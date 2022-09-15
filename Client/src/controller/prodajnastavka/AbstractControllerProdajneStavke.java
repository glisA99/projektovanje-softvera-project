package controller.prodajnastavka;

import communication.Operations;
import communication.Request;
import communication.Response;
import communication.ResponseType;
import controller.general.AbstractController;
import domain.Artikl;
import domain.Klijent;
import domain.ProdajnaStavka;
import exception.ValidationException;
import forms.FrmMain;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.swing.JDialog;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public abstract class AbstractControllerProdajneStavke<T extends JDialog> extends AbstractController<T> {

    protected AbstractControllerProdajneStavke(FrmMain form) {
        super(form);
    }
    
    protected abstract void prepareView() throws Exception;
    
    public List<Klijent> loadClients() throws Exception {
        Request request = new Request();
        request.setOperation(Operations.GET_ALL_CLIENTS);
        
        Response response = this.sendRequest(request);
        
        if (response.getResponseType().equals(ResponseType.SUCCESS)) {
            List<Klijent> clients = (List<Klijent>) response.getResponse();
            return clients;
        } else throw new Exception("Error loading clients");
    }
    
    public List<Artikl> loadArtikls() throws Exception {
        Request request = new Request();
        request.setOperation(Operations.GET_ARTIKLS);
        
        Response response = this.sendRequest(request);
        
        if (response.getResponseType().equals(ResponseType.SUCCESS)) {
            List<Artikl> artikls = (List<Artikl>) response.getResponse();
            return artikls;
        } else throw new Exception("Error loading clients");
    }
    
    public void validateProdajnaStavka(ProdajnaStavka prodajnaStavka) throws ValidationException {
        if (prodajnaStavka.getDatumProdaje() == null) throw new ValidationException("Datum prodaje ne moze biti null");
        if (prodajnaStavka.getDatumProdaje().after(new Date())) throw new ValidationException("Datum prodaje mora biti pre trenutnog datuma");
        if (prodajnaStavka.getIznos().compareTo(BigDecimal.ZERO) != 1) throw new ValidationException("Iznos mora biti veci od nule!");
        if (prodajnaStavka.getKlijentID() == null) throw new ValidationException("Klijent ID ne moze biti null!");
        if (prodajnaStavka.getKolicina() <= 0) throw new ValidationException("Kolicina mora biti veca od nule!");
        if (prodajnaStavka.getRadnikJMBG() == null) throw new ValidationException("Radnik JMBG ne moze biti null!");
        if (prodajnaStavka.getSifraArtikla() == null) throw new ValidationException("Sifra artikla ne moze biti null!");
    }
    
}
