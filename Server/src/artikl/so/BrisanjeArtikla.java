package artikl.so;

import domain.Artikl;
import so.AbstractSystemOperation;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public class BrisanjeArtikla extends AbstractSystemOperation<Artikl> {

    public BrisanjeArtikla() throws Exception {
        super();
    }

    @Override
    protected void precondition(Artikl entity) throws Exception {
        if (entity.getSifraArtikla() == null) throw new Exception("Sifra artikla ne moze biti null!");
    }

    @Override
    protected void executeOperation(Artikl entity) throws Exception {
        boolean res = this.repository.delete(entity);
        if (!res) throw new Exception("Neuspesno brisanje artikla");
    }
    
}
