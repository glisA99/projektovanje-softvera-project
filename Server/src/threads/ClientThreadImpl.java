package threads;

import communication.Receiver;
import communication.Request;
import communication.Response;
import static communication.Operations.*;
import communication.ResponseType;
import communication.Sender;
import domain.Radnik;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import login.so.LoginSO;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public class ClientThreadImpl extends ClientThread {

    public ClientThreadImpl(Socket socket) {
        super(socket);
    }

    @Override
    public void run() {
        while(!this.socket.isClosed()) {
            try {
                Request request = (Request) new Receiver(socket).receive();
                System.out.println("Receiver request from client... Operation type: " + request.getOperation());
                Response response = handleRequest(request);
                new Sender(socket).send(response);
                this.clientStatistic.addRequest(ResponseType.SUCCESS);
                System.out.println("Client request handled sucessfully...");
            } catch (Exception ex) {
                Logger.getLogger(ClientThreadImpl.class.getName()).log(Level.SEVERE, null, ex);
                this.clientStatistic.addRequest(ResponseType.FAILURE);
                System.out.println("Client request failed...");
            }
        }
    }

    private Response handleRequest(Request request) {
        int operation = request.getOperation();
        
        switch(operation) {
            case LOGIN: return handleLogin(request);
        }
        
        return null;
    }

    private Response handleLogin(Request request) {
        Response response = new Response();
        Radnik radnik = (Radnik) request.getData();
        
        try {
            LoginSO so = new LoginSO();
            so.execute(radnik);
            Radnik radnik1 = (Radnik) so.operationResult;
            response.setResponseType(ResponseType.SUCCESS);
            response.setResponse(radnik1);
        } catch (Exception ex) {
            Logger.getLogger(ClientThreadImpl.class.getName()).log(Level.SEVERE, null, ex);
            response.setResponseType(ResponseType.FAILURE);
            response.setException(ex);
        }
        
        return response;
    }
    
}
