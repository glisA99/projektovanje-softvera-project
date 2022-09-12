package controller;

import domain.Klijent;
import java.util.List;
import klijent.so.KreirajKlijenta;
import klijent.so.PronadjiKlijente;
import klijent.so.UcitajKlijente;
import klijent.so.ZapamtiKlijenta;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public class ClientController implements IController<Klijent> {

    private static ClientController instance;

    private ClientController() {
    }

    public static ClientController getInstance() {
        if (instance == null) {
            instance = new ClientController();
        }
        return instance;
    }

    @Override
    public Klijent save(Klijent t) throws Exception {
        ZapamtiKlijenta zapamtiKlijentaSO = new ZapamtiKlijenta();
        zapamtiKlijentaSO.execute(t);
        Klijent _client = (Klijent) zapamtiKlijentaSO.operationResult;
        return _client;
    }

    @Override
    public Klijent create() throws Exception {
        KreirajKlijenta kreirajKlijentaSO = new KreirajKlijenta();
        kreirajKlijentaSO.execute(new Klijent());
        Klijent klijent = (Klijent) kreirajKlijentaSO.operationResult;
        return klijent;
    }

    @Override
    public void delete(Klijent t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Klijent> findAll() throws Exception {
        UcitajKlijente ucitajKlijenteSO = new UcitajKlijente();

        ucitajKlijenteSO.execute(new Klijent());

        List<Klijent> clients = (List<Klijent>) ucitajKlijenteSO.operationResult;
        return clients;
    }

    @Override
    public List<Klijent> findAllCustom(Klijent t) throws Exception {
        PronadjiKlijente pronadjiKlijenteSO = new PronadjiKlijente();
        pronadjiKlijenteSO.execute(t);
        List<Klijent> clients = (List<Klijent>) pronadjiKlijenteSO.operationResult;
        return clients;
    }

    @Override
    public Klijent findOne(Klijent t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
