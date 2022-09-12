package izvestaj.so;

import domain.Izvestaj;
import domain.StavkaIzvestaja;
import java.util.Date;
import java.util.List;
import so.AbstractSystemOperation;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public class ZapamtiIzvestaj extends AbstractSystemOperation<Izvestaj> {

    public ZapamtiIzvestaj() throws Exception {
        super();
    }
    

    @Override
    protected void precondition(Izvestaj entity) throws Exception {
        if (entity.getDatumOd() == null) throw new Exception("Datum Od ne moze biti null!");
        // if datum do is not specified, use current date
        if (entity.getDatumDo() == null) entity.setDatumDo(new Date());
        if (entity.getRadnikJMBG() == null) throw new Exception("Radnik JMBG ne moze biti null!");
        if (entity.getDatumOd().before(entity.getDatumDo())) {
            throw new Exception("DatumOd mora biti pre datuma DatumDo!");
        }
        if (entity.getDatumDo().after(new Date())) {
            throw new Exception("Datum DatumDo mora biti pre danasnjeg datuma (DatumKreiranja)!");
        }
        if (entity.getStavke() == null) throw new Exception("Stavke izvestaja ne mogu biti null!");
        if (entity.getStavke().size() == 0) throw new Exception("Izvestaj mora imati bar jednu stavku!");
    }

    @Override
    protected void executeOperation(Izvestaj entity) throws Exception {
        Izvestaj izvestaj = (Izvestaj) this.repository.save(entity);
        
        Long generatedID = izvestaj.getIzvestajID();
        List<StavkaIzvestaja> stavke = izvestaj.getStavke();
        for(int i = 0;i < stavke.size();i++) {
            StavkaIzvestaja stavka = stavke.get(i);
            stavka.setRB(i);
            stavka.setIzvestajID(generatedID);
            this.repository.save(stavka);
        }
        
        this.operationResult = izvestaj;
    }

}
