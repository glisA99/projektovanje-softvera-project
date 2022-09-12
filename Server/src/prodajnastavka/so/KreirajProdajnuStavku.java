package prodajnastavka.so;

import domain.ProdajnaStavka;
import so.AbstractSystemOperation;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public class KreirajProdajnuStavku extends AbstractSystemOperation<ProdajnaStavka> {

    public KreirajProdajnuStavku() throws Exception {
        super();
    }

    @Override
    protected void precondition(ProdajnaStavka entity) throws Exception {
        return;
    }

    @Override
    protected void executeOperation(ProdajnaStavka entity) throws Exception {
        ProdajnaStavka prodajnaStavka = new ProdajnaStavka();
        this.operationResult = prodajnaStavka;
    }
    
}
