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
                Response response = handleRequest(request);
                new Sender(socket).send(response);
                this.clientStatistic.addRequest(ResponseType.SUCCESS);
            } catch (Exception ex) {
                Logger.getLogger(ClientThreadImpl.class.getName()).log(Level.SEVERE, null, ex);
                this.clientStatistic.addRequest(ResponseType.FAILURE);
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
        
        return response;
    }
    
}
