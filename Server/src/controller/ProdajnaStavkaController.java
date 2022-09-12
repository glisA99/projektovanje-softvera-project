package controller;

import domain.ProdajnaStavka;
import java.util.List;
import prodajnastavka.so.KreirajProdajnuStavku;
import prodajnastavka.so.ObrisiProdajnuStavku;
import prodajnastavka.so.PretraziProdajneStavke;
import prodajnastavka.so.ZapamtiProdajnuStavku;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public class ProdajnaStavkaController implements IController<ProdajnaStavka>{

    private static ProdajnaStavkaController instance;
    
    private ProdajnaStavkaController() {}
    
    public static ProdajnaStavkaController getInstance() {
        if (instance == null) {
            instance = new ProdajnaStavkaController();
        }
        return instance;
    }

    @Override
    public ProdajnaStavka create() throws Exception {
        KreirajProdajnuStavku kreirajProdajnuStavkuSO = new KreirajProdajnuStavku();
        
        kreirajProdajnuStavkuSO.execute(new ProdajnaStavka());
        
        ProdajnaStavka prodajnaStavka = (ProdajnaStavka) kreirajProdajnuStavkuSO.operationResult;
        return prodajnaStavka;
    }

    @Override
    public ProdajnaStavka save(ProdajnaStavka t) throws Exception {
        ZapamtiProdajnuStavku zapamtiProdajnuStavkuSO = new ZapamtiProdajnuStavku();
        
        zapamtiProdajnuStavkuSO.execute(t);
        
        ProdajnaStavka prodajnaStavka = (ProdajnaStavka) zapamtiProdajnuStavkuSO.operationResult;
        return prodajnaStavka;
    }

    @Override
    public void delete(ProdajnaStavka t) throws Exception {
        ObrisiProdajnuStavku obrisiProdajnuStavkuSO = new ObrisiProdajnuStavku();
        obrisiProdajnuStavkuSO.execute(t);
    }

    @Override
    public List<ProdajnaStavka> findAll() throws Exception {
        PretraziProdajneStavke pretraziProdajneStavkeSO = new PretraziProdajneStavke();
        
        pretraziProdajneStavkeSO.execute(new ProdajnaStavka());
        
        List<ProdajnaStavka> stavke = (List<ProdajnaStavka>) pretraziProdajneStavkeSO.operationResult;
        return stavke;
    }

    @Override
    public List<ProdajnaStavka> findAllCustom(ProdajnaStavka t) throws Exception {
        PretraziProdajneStavke pretraziProdajneStavkeSO = new PretraziProdajneStavke();
        
        pretraziProdajneStavkeSO.execute(t);
        
        List<ProdajnaStavka> stavke = (List<ProdajnaStavka>) pretraziProdajneStavkeSO.operationResult;
        return stavke;
    }

    @Override
    public ProdajnaStavka findOne(ProdajnaStavka t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
