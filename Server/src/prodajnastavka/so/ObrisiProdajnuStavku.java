package prodajnastavka.so;

import domain.ProdajnaStavka;
import so.AbstractSystemOperation;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public class ObrisiProdajnuStavku extends AbstractSystemOperation<ProdajnaStavka> {

    public ObrisiProdajnuStavku() throws Exception {
        super();
    }

    @Override
    protected void precondition(ProdajnaStavka entity) throws Exception {
        if (entity.getProdajnaStavkaID() == null) throw new Exception("ProdajnaStavka ID ne moze biti null!");
    }

    @Override
    protected void executeOperation(ProdajnaStavka entity) throws Exception {
        boolean res = this.repository.delete(entity);
        if (!res) throw new Exception("Neuspesno brisanje prodajne stavke!");
    }
    
}
