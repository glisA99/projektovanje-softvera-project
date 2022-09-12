package artikl.so;

import so.AbstractSystemOperation;
import domain.Artikl;
import java.math.BigDecimal;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public class ZapamtiArtikl extends AbstractSystemOperation<Artikl> {

    public ZapamtiArtikl() throws Exception {
        super();
    }

    @Override
    protected void precondition(Artikl entity) throws Exception {
        if (entity.getSifraArtikla() == null) throw new Exception("Sifra artikla ne moze biti null!");
        if (entity.getNaziv() == null) throw new Exception("Naziv artikla ne moze biti null!");
        if (entity.getNaziv().length() <= 2) throw new Exception("Naziv artikla mora biti min 3 karaktera dugacak!");
        if (entity.getOpis() == null) throw new Exception("Opis artikla ne moze biti null!");
        if (entity.getKolicinaNaStanju() < 0) throw new Exception("Kolicina na stanju ne moze biti manja od nule!");
        if (entity.getVelicina() == null) throw new Exception("Velicina ne moze biti null!");
        if (entity.getProizvodjac() == null) throw new Exception("Proizvodjac ne moze biti null!");
        if (entity.getProdajnaCena() == null) throw new Exception("Prodajna cena ne moze biti null!");
        if (entity.getProdajnaCena().compareTo(new BigDecimal(0)) != 1) {
            throw new Exception("Prodajna cena mora biti veca od nule!");
        }
        if (entity.getProdajnaVrednost() == null) throw new Exception("Prodajna vrednost ne moze biti null!");
        if (entity.getProdajnaVrednost().compareTo(new BigDecimal(0)) != 1) {
            throw new Exception("Prodajna vrednost mora biti veca od nule!");
        }
    }

    @Override
    protected void executeOperation(Artikl entity) throws Exception {
        Artikl artikl = (Artikl) this.repository.findByID(entity);
        
        if (artikl != null) {
            // Artikl already exits -> UPDATE
            boolean res = this.repository.update(entity);
            if (!res) throw new Exception("Artikl is not updated!");
            return;
        }
        
        // Artikl doesn't exists -> INSERT
        Artikl _artikl = (Artikl) this.repository.save(entity);
        this.operationResult = _artikl;
    }
    
    
    
}
