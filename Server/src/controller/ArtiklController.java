package controller;

import artikl.so.BrisanjeArtikla;
import artikl.so.KreirajArtikl;
import artikl.so.PretraziArtikle;
import artikl.so.PronadjiArtikl;
import artikl.so.ZapamtiArtikl;
import domain.Artikl;
import java.util.List;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public class ArtiklController implements IController<Artikl> {

    private static ArtiklController instance;
    
    private ArtiklController() {}
    
    public static ArtiklController getInstance() {
        if (instance == null) {
            instance = new ArtiklController();
        }
        return instance;
    }

    @Override
    public Artikl save(Artikl t) throws Exception {
        ZapamtiArtikl zapamtiArtikl = new ZapamtiArtikl();
        
        zapamtiArtikl.execute(t);
        
        Artikl artikl1 = (Artikl) zapamtiArtikl.operationResult;
        return artikl1;
    }

    @Override
    public Artikl create() throws Exception {
        KreirajArtikl kreirajArtiklSO = new KreirajArtikl();
        
        kreirajArtiklSO.execute(new Artikl());
        
        Artikl artikl = (Artikl) kreirajArtiklSO.operationResult;
        return artikl;
    }

    @Override
    public void delete(Artikl t) throws Exception {
        BrisanjeArtikla brisanjeArtiklaSO = new BrisanjeArtikla();
        brisanjeArtiklaSO.execute(t);
    }

    @Override
    public List<Artikl> findAll() throws Exception {
        PretraziArtikle pretraziArtikleSO = new PretraziArtikle();
        
        pretraziArtikleSO.execute(new Artikl());
        
        List<Artikl> artikli = (List<Artikl>) pretraziArtikleSO.operationResult;
        return artikli;
    }

    @Override
    public List<Artikl> findAllCustom(Artikl t) throws Exception {
        PretraziArtikle pretraziArtikleSO = new PretraziArtikle();
        
        pretraziArtikleSO.execute(t);
        
        List<Artikl> artikli = (List<Artikl>) pretraziArtikleSO.operationResult;
        return artikli;
    }

    @Override
    public Artikl findOne(Artikl t) throws Exception {
        PronadjiArtikl pronadjiArtiklSO = new PronadjiArtikl();
        
        pronadjiArtiklSO.execute(t);
        
        Artikl artikl1 = (Artikl) pronadjiArtiklSO.operationResult;
        return artikl1;
    }
    
}
