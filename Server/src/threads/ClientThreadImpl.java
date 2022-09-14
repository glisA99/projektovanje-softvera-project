package threads;

import communication.Operations;
import communication.Receiver;
import communication.Request;
import communication.Response;
import static communication.Operations.*;
import communication.ResponseType;
import communication.Sender;
import controller.ArtiklController;
import controller.Authentication;
import controller.ClientController;
import controller.IzvestajController;
import controller.ProdajnaStavkaController;
import domain.Klijent;
import domain.Radnik;
import domain.Artikl;
import domain.Izvestaj;
import domain.ProdajnaStavka;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
                }
                
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
            }
        }
        
        this.serverThread.disconnectClient(this);
    }

    private Response handleRequest(Request request) {
        int operation = request.getOperation();

        switch (operation) {
            case LOGIN:
                return handleLogin(request);
            // client operations
            case GET_ALL_CLIENTS:
                return handleFetchAllClients();
            case GET_CLIENTS_CONDITIONAL:
                return handleFetchAllClientsConditional(request);
            case CREATE_CLIENT:
                return handleCreateClient();
            case SAVE_CLIENT:
                return handleSaveClient(request);
            // artikl operations
            case CREATE_ARTIKL:
                return handleCreateArtikl();
            case SAVE_ARTIKL:
                return handleSaveArtikl(request);
            case DELETE_ARTIKL:
                return handleDeleteArtikl(request);
            case FIND_ARTIKL:
                return handleFindArtikl(request);
            case SEARCH_ARTIKLS:
                return handleSearchArtikls(request);
            case GET_ARTIKLS:
                return handleGetArtikls();
            // prodajna stavka operations
            case DELETE_PRODAJNA_STAVKA:
                return handleDeleteProdajnaStavka(request);
            case CREATE_PRODAJNA_STAVKA:
                return handleCreateProdajnaStavka();
            case SAVE_PRODAJNA_STAVKA:
                return handleSaveProdajnaStavka(request);
            case SEARCH_PRODAJNE_STAVKE:
                return handleSearchProdajneStavke(request);
            // izvestaj operations
            case CREATE_IZVESTAJ:
                return handleCreateIzvestaj(request);
            case SAVE_IZVESTAJ:
                return handleSaveIzvestaj(request);
        }

        return null;
    }

    private Response handleLogin(Request request) {
        Response response = new Response();
        Radnik radnik = (Radnik) request.getData();

        try {
            Radnik radnik1 = Authentication.getInstance().login(radnik);
            response.setResponseType(ResponseType.SUCCESS);
            radnik1.setUsername(radnik.getUsername());
            radnik1.setPassword(radnik.getPassword());
            response.setResponse(radnik1);
        } catch (Exception ex) {
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
            // set logged radnik - if opertaions is LOGIN
            if (operation == Operations.LOGIN) {
                Radnik radnik = (Radnik) response.getResponse();
                this.clientStatistic.setLoggedRadnik(radnik);
            }
        } else {
            this.clientStatistic.addRequest(ResponseType.FAILURE);
        }
    }

    private Response handleFetchAllClients() {
        Response response = new Response();

        try {
            List<Klijent> clients = ClientController.getInstance().findAll();
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
            List<Klijent> clients = ClientController.getInstance().findAllCustom(client);
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
            Klijent _client = ClientController.getInstance().save(client);
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
            Klijent klijent = ClientController.getInstance().create();
            response.setResponseType(ResponseType.SUCCESS);
            response.setResponse(klijent);
        } catch (Exception ex) {
            Logger.getLogger(ClientThreadImpl.class.getName()).log(Level.SEVERE, null, ex);
            response.setResponseType(ResponseType.FAILURE);
            response.setException(ex);
        }

        return response;
    }

    private Response handleCreateArtikl() {
        Response response = new Response();

        try {
            Artikl artikl = ArtiklController.getInstance().create();
            response.setResponseType(ResponseType.SUCCESS);
            response.setResponse(artikl);
        } catch (Exception ex) {
            Logger.getLogger(ClientThreadImpl.class.getName()).log(Level.SEVERE, null, ex);
            response.setResponseType(ResponseType.FAILURE);
            response.setException(ex);
        }

        return response;
    }

    private Response handleSaveArtikl(Request request) {
        Response response = new Response();
        Artikl a = (Artikl) request.getData();

        try {
            Artikl artikl = ArtiklController.getInstance().save(a);
            response.setResponseType(ResponseType.SUCCESS);
            response.setResponse(artikl);
        } catch (Exception ex) {
            Logger.getLogger(ClientThreadImpl.class.getName()).log(Level.SEVERE, null, ex);
            response.setResponseType(ResponseType.FAILURE);
            response.setException(ex);
        }

        return response;
    }

    private Response handleDeleteArtikl(Request request) {
        Response response = new Response();
        Artikl a = (Artikl) request.getData();

        try {
            ArtiklController.getInstance().delete(a);
            response.setResponseType(ResponseType.SUCCESS);
            response.setResponse(null);
        } catch (Exception ex) {
            Logger.getLogger(ClientThreadImpl.class.getName()).log(Level.SEVERE, null, ex);
            response.setResponseType(ResponseType.FAILURE);
            response.setException(ex);
        }

        return response;
    }

    private Response handleFindArtikl(Request request) {
        Response response = new Response();
        Artikl a = (Artikl) request.getData();

        try {
            Artikl artikl = ArtiklController.getInstance().findOne(a);
            response.setResponseType(ResponseType.SUCCESS);
            response.setResponse(artikl);
        } catch (Exception ex) {
            Logger.getLogger(ClientThreadImpl.class.getName()).log(Level.SEVERE, null, ex);
            response.setResponseType(ResponseType.FAILURE);
            response.setException(ex);
        }

        return response;
    }

    private Response handleSearchArtikls(Request request) {
        Response response = new Response();
        Artikl a = (Artikl) request.getData();

        try {
            List<Artikl> artikli = ArtiklController.getInstance().findAllCustom(a);
            response.setResponseType(ResponseType.SUCCESS);
            response.setResponse(artikli);
        } catch (Exception ex) {
            Logger.getLogger(ClientThreadImpl.class.getName()).log(Level.SEVERE, null, ex);
            response.setResponseType(ResponseType.FAILURE);
            response.setException(ex);
        }

        return response;
    }

    private Response handleGetArtikls() {
        Response response = new Response();

        try {
            List<Artikl> artikli = ArtiklController.getInstance().findAll();
            response.setResponseType(ResponseType.SUCCESS);
            response.setResponse(artikli);
        } catch (Exception ex) {
            Logger.getLogger(ClientThreadImpl.class.getName()).log(Level.SEVERE, null, ex);
            response.setResponseType(ResponseType.FAILURE);
            response.setException(ex);
        }

        return response;
    }

    private Response handleDeleteProdajnaStavka(Request request) {
        Response response = new Response();
        ProdajnaStavka ps = (ProdajnaStavka) request.getData();

        try {
            ProdajnaStavkaController.getInstance().delete(ps);
            response.setResponseType(ResponseType.SUCCESS);
            response.setResponse(null);
        } catch (Exception ex) {
            Logger.getLogger(ClientThreadImpl.class.getName()).log(Level.SEVERE, null, ex);
            response.setResponseType(ResponseType.FAILURE);
            response.setException(ex);
        }

        return response;
    }

    private Response handleCreateProdajnaStavka() {
        Response response = new Response();

        try {
            ProdajnaStavka prodajnaStavka = ProdajnaStavkaController.getInstance().create();
            response.setResponseType(ResponseType.SUCCESS);
            response.setResponse(prodajnaStavka);
        } catch (Exception ex) {
            Logger.getLogger(ClientThreadImpl.class.getName()).log(Level.SEVERE, null, ex);
            response.setResponseType(ResponseType.FAILURE);
            response.setException(ex);
        }

        return response;
    }

    private Response handleSaveProdajnaStavka(Request request) {
        Response response = new Response();
        ProdajnaStavka ps = (ProdajnaStavka) request.getData();

        try {
            ProdajnaStavka prodajnaStavka = ProdajnaStavkaController.getInstance().save(ps);
            response.setResponseType(ResponseType.SUCCESS);
            response.setResponse(prodajnaStavka);
        } catch (Exception ex) {
            Logger.getLogger(ClientThreadImpl.class.getName()).log(Level.SEVERE, null, ex);
            response.setResponseType(ResponseType.FAILURE);
            response.setException(ex);
        }

        return response;
    }

    private Response handleSearchProdajneStavke(Request request) {
        Response response = new Response();
        ProdajnaStavka ps = (ProdajnaStavka) request.getData();

        try {
            List<ProdajnaStavka> stavke = ProdajnaStavkaController.getInstance().findAllCustom(ps);
            response.setResponseType(ResponseType.SUCCESS);
            response.setResponse(stavke);
        } catch (Exception ex) {
            Logger.getLogger(ClientThreadImpl.class.getName()).log(Level.SEVERE, null, ex);
            response.setResponseType(ResponseType.FAILURE);
            response.setException(ex);
        }

        return response;
    }

    private Response handleCreateIzvestaj(Request request) {
        Response response = new Response();
        Izvestaj i = (Izvestaj) request.getData();

        try {
            Izvestaj izvestaj = IzvestajController.getInstance().create(i);
            response.setResponseType(ResponseType.SUCCESS);
            response.setResponse(izvestaj);
        } catch (Exception ex) {
            Logger.getLogger(ClientThreadImpl.class.getName()).log(Level.SEVERE, null, ex);
            response.setResponseType(ResponseType.FAILURE);
            response.setException(ex);
        }

        return response;
    }

    private Response handleSaveIzvestaj(Request request) {
        Response response = new Response();
        Izvestaj i = (Izvestaj) request.getData();

        try {
            Izvestaj izvestaj = IzvestajController.getInstance().save(i);
            response.setResponseType(ResponseType.SUCCESS);
            response.setResponse(izvestaj);
        } catch (Exception ex) {
            Logger.getLogger(ClientThreadImpl.class.getName()).log(Level.SEVERE, null, ex);
            response.setResponseType(ResponseType.FAILURE);
            response.setException(ex);
        }

        return response;
    }

}
