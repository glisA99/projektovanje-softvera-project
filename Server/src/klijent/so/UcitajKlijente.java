package klijent.so;

import domain.IEntity;
import domain.Klijent;
import java.util.List;
import so.AbstractSystemOperation;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public class UcitajKlijente extends AbstractSystemOperation<Klijent> {

    public UcitajKlijente() throws Exception {
        super();
    }

    @Override
    protected void precondition() throws Exception {
        return;
    }

    @Override
    protected void executeOperation(Klijent entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void executeOperation(Klijent entity, List<Klijent> entities) throws Exception {
        List<IEntity> ents = this.repository.findAll(entity);
        for(IEntity _entity : ents) {
            entities.add((Klijent) _entity);
        }
    }

    @Override
    protected void executeOperation(Klijent entity, Object param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
