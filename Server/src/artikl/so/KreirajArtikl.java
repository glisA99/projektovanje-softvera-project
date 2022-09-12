package artikl.so;

import domain.Artikl;
import so.AbstractSystemOperation;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public class KreirajArtikl extends AbstractSystemOperation<Artikl> {

    public KreirajArtikl() throws Exception {
        super();
    }

    @Override
    protected void precondition(Artikl entity) throws Exception {
        return;
    }

    @Override
    protected void executeOperation(Artikl entity) throws Exception {
        Artikl artikl = new Artikl();
        this.operationResult = artikl;
    }
    
}
