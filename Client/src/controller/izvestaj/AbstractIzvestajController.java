package controller.izvestaj;

import communication.Operations;
import communication.Request;
import communication.Response;
import communication.ResponseType;
import controller.general.AbstractController;
import domain.Artikl;
import domain.Izvestaj;
import domain.Klijent;
import exception.ValidationException;
import forms.FrmMain;
import java.util.Date;
import java.util.List;
import javax.swing.JDialog;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public abstract class AbstractIzvestajController<T extends JDialog> extends AbstractController<T> {

    protected AbstractIzvestajController(FrmMain form) {
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
    
    protected void validateIzvestaj(Izvestaj izvestaj) throws ValidationException {
        if (izvestaj.getRadnikJMBG() == null) {
            throw new ValidationException("Radnik JMBG ne moze biti null!");
        }
    }

}
