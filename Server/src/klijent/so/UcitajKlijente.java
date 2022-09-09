package klijent.so;

import domain.IEntity;
import domain.Klijent;
import java.util.ArrayList;
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
    protected void precondition(Klijent param) throws Exception {
        // no precondition
        return;
    }

    @Override
    protected void executeOperation(Klijent entity) throws Exception {
        List<Klijent> clients = new ArrayList<>();
        List<IEntity> entities;

        entities = this.repository.findAll(entity);

        entities.forEach(_entity -> clients.add((Klijent) _entity));
        this.operationResult = clients;
    }

}
