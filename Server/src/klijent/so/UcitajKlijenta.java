package klijent.so;

import domain.IEntity;
import domain.Klijent;
import java.util.List;
import so.AbstractSystemOperation;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public class UcitajKlijenta extends AbstractSystemOperation<Klijent> {

    public UcitajKlijenta() throws Exception {
        super();
    }
    
    @Override
    protected void precondition(Klijent entity) throws Exception {
        if (entity.getKlijentID() == null) throw new Exception("KlijentID can NOT be null!");
    }

    @Override
    protected void executeOperation(Klijent entity) throws Exception {
        IEntity _entity = this.repository.findByID(entity);
        if (entity == null) throw new Exception("Klijent not found!");
        this.operationResult = (Klijent) _entity;
    }

}
