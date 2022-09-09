package threads;

import communication.Operations;
import communication.Receiver;
import communication.Request;
import communication.Response;
import static communication.Operations.*;
import communication.ResponseType;
import communication.Sender;
import domain.Radnik;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import login.so.LoginSO;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public class ClientThreadImpl extends ClientThread {

    public ClientThreadImpl(Socket socket, ServerThread serverThread) {
        super(socket, serverThread);
    }

    @Override
    public void run() {
        while (!this.socket.isClosed()) {
            try {
                Request request = null;
                try {
                    request = (Request) new Receiver(socket).receive();
                } catch (Exception ex) {
                    this.socket.close();
                    System.out.println("Closing client socket...");
                }
                System.out.println("Receiver request from client... Operation type: " + request.getOperation());
                Response response = handleRequest(request);
                new Sender(socket).send(response);
                processRequestForStatistics(request, response);
            } catch (Exception ex) {
                try {
                    this.socket.close();
                } catch (IOException ex1) {
                    Logger.getLogger(ClientThreadImpl.class.getName()).log(Level.SEVERE, null, ex1);
                }
                Logger.getLogger(ClientThreadImpl.class.getName()).log(Level.SEVERE, null, ex);
                this.clientStatistic.addRequest(ResponseType.FAILURE);
                System.out.println("Client request failed...");
            }
        }
        System.out.println("Client disconnected...");
        this.serverThread.disconnectClient(this);
    }

    private Response handleRequest(Request request) {
        int operation = request.getOperation();

        switch (operation) {
            case LOGIN:
                return handleLogin(request);
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
            if (radnik1 == null) {
                throw new Exception("Radnik not found");
            }
            response.setResponseType(ResponseType.SUCCESS);
            response.setResponse(radnik1);
        } catch (Exception ex) {
            System.out.println("Radnik NOT found...");
            Logger.getLogger(ClientThreadImpl.class.getName()).log(Level.SEVERE, null, ex);
            response.setResponseType(ResponseType.FAILURE);
            response.setException(ex);
        }

        return response;
    }

    private void processRequestForStatistics(Request request, Response response) {
        int operation = request.getOperation();
        // add statistic
        if (response.getResponseType().equals(ResponseType.SUCCESS)) {
            this.clientStatistic.addRequest(ResponseType.SUCCESS);
            Radnik radnik = (Radnik) response.getResponse();
            // set logged radnik
            if (operation == Operations.LOGIN) this.clientStatistic.setLoggedRadnik(radnik);
            System.out.println("Client request handled sucessfully...");
        } else {
            System.out.println("Client request failed...");
            this.clientStatistic.addRequest(ResponseType.FAILURE);
        }
    }

}
