package controller;

import domain.Izvestaj;
import izvestaj.so.KreirajIzvestaj;
import izvestaj.so.ZapamtiIzvestaj;
import java.util.List;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public class IzvestajController {

    private static IzvestajController instance;
    
    private IzvestajController() {}
    
    public static IzvestajController getInstance() {
        if (instance == null) {
            instance = new IzvestajController();
        }
        return instance;
    }

    public Izvestaj save(Izvestaj t) throws Exception {
        ZapamtiIzvestaj zapamtiIzvestajSO = new ZapamtiIzvestaj();
        
        zapamtiIzvestajSO.execute(t);
        
        Izvestaj izvestaj = (Izvestaj) zapamtiIzvestajSO.operationResult;
        return izvestaj;
    }
    
    public Izvestaj create(Izvestaj izvestaj) throws Exception {
        KreirajIzvestaj kreirajIzvestajSO = new KreirajIzvestaj();
        
        kreirajIzvestajSO.execute(izvestaj);
        
        Izvestaj izvestaj1 = (Izvestaj) kreirajIzvestajSO.operationResult;
        return izvestaj1;
    }
    
}
