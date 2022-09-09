package threads;

import communication.Operations;
import communication.Receiver;
import communication.Request;
import communication.Response;
import static communication.Operations.*;
import communication.ResponseType;
import communication.Sender;
import domain.Klijent;
import domain.Radnik;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import klijent.so.KreirajKlijenta;
import klijent.so.PronadjiKlijente;
import klijent.so.UcitajKlijente;
import klijent.so.ZapamtiKlijenta;
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
            case LOGIN: return handleLogin(request);
            case GET_ALL_CLIENTS: return handleFetchAllClients();
            case GET_CLIENTS_CONDITIONAL: return handleFetchAllClientsConditional(request);
            case CREATE_CLIENT: return handleCreateClient();
            case SAVE_CLIENT: return handleSaveClient(request);
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

    private Response handleFetchAllClients() {
        Response response = new Response();
        
        try {
            UcitajKlijente ucitajKlijenteSO = new UcitajKlijente();
            ucitajKlijenteSO.execute(new Klijent());
            List<Klijent> clients = (List<Klijent>) ucitajKlijenteSO.operationResult;
            response.setResponseType(ResponseType.SUCCESS);
            response.setResponse(clients);
        } catch (Exception ex) {
            Logger.getLogger(ClientThreadImpl.class.getName()).log(Level.SEVERE, null, ex);
            response.setResponseType(ResponseType.FAILURE);
            response.setException(ex);
        }
        
        return response;
    }

    private Response handleFetchAllClientsConditional(Request request) {
        Response response = new Response();
        Klijent client = (Klijent) request.getData();
        
        try {
            PronadjiKlijente pronadjiKlijenteSO = new PronadjiKlijente();
            pronadjiKlijenteSO.execute(client);
            List<Klijent> clients = (List<Klijent>) pronadjiKlijenteSO.operationResult;
            response.setResponseType(ResponseType.SUCCESS);
            response.setResponse(clients);
        } catch (Exception ex) {
            Logger.getLogger(ClientThreadImpl.class.getName()).log(Level.SEVERE, null, ex);
            response.setResponseType(ResponseType.FAILURE);
            response.setException(ex);
        }
        
        return response;
    }

    private Response handleSaveClient(Request request) {
        Response response = new Response();
        Klijent client = (Klijent) request.getData();
        
        try {
            ZapamtiKlijenta zapamtiKlijentaSO = new ZapamtiKlijenta();
            zapamtiKlijentaSO.execute(client);
            Klijent _client = (Klijent) zapamtiKlijentaSO.operationResult;
            response.setResponseType(ResponseType.SUCCESS);
            response.setResponse(_client);
        } catch (Exception ex) {
            Logger.getLogger(ClientThreadImpl.class.getName()).log(Level.SEVERE, null, ex);
            response.setResponseType(ResponseType.FAILURE);
            response.setException(ex);
        }
        
        return response;
    }

    private Response handleCreateClient() {
        Response response = new Response();
        
        try {
            KreirajKlijenta kreirajKlijentaSO = new KreirajKlijenta();
            kreirajKlijentaSO.execute(new Klijent());
            Klijent klijent = (Klijent) kreirajKlijentaSO.operationResult;
            response.setResponseType(ResponseType.SUCCESS);
            response.setResponse(klijent);
        } catch (Exception ex) {
            Logger.getLogger(ClientThreadImpl.class.getName()).log(Level.SEVERE, null, ex);
            response.setResponseType(ResponseType.FAILURE);
            response.setException(ex);
        }
        
        return response;
    }

}
