package prodajnastavka.so;

import domain.ProdajnaStavka;
import java.math.BigDecimal;
import so.AbstractSystemOperation;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public class ZapamtiProdajnuStavku extends AbstractSystemOperation<ProdajnaStavka> {

    public ZapamtiProdajnuStavku() throws Exception {
        super();
    }

    @Override
    protected void precondition(ProdajnaStavka entity) throws Exception {
        if (entity.getRadnikJMBG() == null) throw new Exception("RadnikJMBG ne moze biti null!");
        if (entity.getDatumProdaje() == null) throw new Exception("Datum prodaje ne moze biti null!");
        if (entity.getKolicina() <= 0) throw new Exception("Kolicina mora biti veca od nule!");
        if (entity.getIznos().compareTo(BigDecimal.ZERO) != 1) throw new Exception("Iznos mora biti veca od nule!");
        if (entity.getSifraArtikla() == null) throw new Exception("Sifra artikla ne moze biti null!");
    }

    @Override
    protected void executeOperation(ProdajnaStavka entity) throws Exception {
        ProdajnaStavka prodajnaStavka = null;
        if (entity.getProdajnaStavkaID() != null) {
            prodajnaStavka = (ProdajnaStavka) this.repository.findByID(entity);
        }
        
        if (prodajnaStavka != null) {
            // ProdajnaStavka already exits -> UPDATE
            boolean res = this.repository.update(entity);
            if (!res) throw new Exception("ProdajnaStavka NOT updated!");
            return;
        }
        
        // ProdajnaStavka doesn't exits -> INSERT
        ProdajnaStavka _prodajnaStavka = (ProdajnaStavka) this.repository.save(entity);
        this.operationResult = _prodajnaStavka;
    }
    
}
