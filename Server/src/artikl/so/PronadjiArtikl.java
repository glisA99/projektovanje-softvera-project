package artikl.so;

import domain.Artikl;
import so.AbstractSystemOperation;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public class PronadjiArtikl extends AbstractSystemOperation<Artikl> {

    public PronadjiArtikl() throws Exception {
        super();
    }

    @Override
    protected void precondition(Artikl entity) throws Exception {
        if (entity.getSifraArtikla() == null) throw new Exception("Sifra artikla ne moze biti null!");
    }

    @Override
    protected void executeOperation(Artikl entity) throws Exception {
        Artikl artikl = (Artikl) this.repository.findByID(entity);
        if (artikl == null) throw new Exception("Artikl not found");
        this.operationResult = artikl;
    }

    
    
}
