package klijent.so;

import domain.Klijent;
import so.AbstractSystemOperation;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public class ZapamtiKlijenta extends AbstractSystemOperation<Klijent> {

    public ZapamtiKlijenta() throws Exception {
        super();
    }
    
    @Override
    protected void precondition(Klijent entity) throws Exception {
        if (entity.getIme() == null) throw new Exception("Ime can NOT be null!");
        if (entity.getIme().length() <= 1) throw new Exception("Ime must be at least 2 characters long!");
        if (entity.getPrezime()== null) throw new Exception("Prezime can NOT be null!");
        if (entity.getPrezime().length() <= 1) throw new Exception("Prezime must be at least 2 characters long!");
        if (entity.getEmail() == null) throw new Exception("Email can NOT be null!");
    }

    @Override
    protected void executeOperation(Klijent entity) throws Exception {
        Klijent client = null;
        if (entity.getKlijentID() != null) {
            client = (Klijent) this.repository.findByID(entity);
        }
        
        if (client != null) {
            // if klijent already exists - UPDATE
            boolean result = this.repository.update(entity);
            if (!result) throw new Exception("Klijent is NOT updated!");
            return;
        }
        
        // else CREATE new
        Klijent _client = (Klijent) this.repository.save(entity);
        this.operationResult = client;
    }

}
