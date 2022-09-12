package izvestaj.so;

import domain.IEntity;
import domain.Izvestaj;
import domain.ProdajnaStavka;
import domain.StavkaIzvestaja;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import so.AbstractSystemOperation;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public class KreirajIzvestaj extends AbstractSystemOperation<Izvestaj> {
    
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-DD");

    public KreirajIzvestaj() throws Exception {
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
    }

    @Override
    protected void executeOperation(Izvestaj entity) throws Exception {
        // create izvestaj domain object
        Date datumOd = entity.getDatumOd();
        Date datumDo = entity.getDatumDo();
        Izvestaj izvestaj = new Izvestaj();
        izvestaj.setRadnikJMBG(entity.getRadnikJMBG());
        izvestaj.setDatumDo(entity.getDatumDo());
        izvestaj.setDatumOd(entity.getDatumOd());
        izvestaj.setDatumKreiranja(new Date());
        
        // find all ProdajneStavke which date of sale is between datumOd and datumDo
        List<ProdajnaStavka> prodajneStavke = findAllProdajneStavkeBetween(izvestaj, datumOd,datumDo);
        
        List<StavkaIzvestaja> stavke = new ArrayList<>(prodajneStavke.size());
        BigDecimal ukupanPrihod = BigDecimal.ZERO;
        for(int i = 0;i < prodajneStavke.size();i++) {
            StavkaIzvestaja stavkaIzvestaja = new StavkaIzvestaja();
            stavkaIzvestaja.setRB(1);
            BigDecimal prihodStavke = prodajneStavke.get(i).getIznos();
            ukupanPrihod.add(prihodStavke);
            stavkaIzvestaja.setPrihodStavke(prihodStavke);
        }
        
        izvestaj.setStavke(stavke);
        izvestaj.setUkupanPrihod(ukupanPrihod);
        
        this.operationResult = izvestaj;
    }

    private List<ProdajnaStavka> findAllProdajneStavkeBetween(Izvestaj entity, Date datumOd, Date datumDo) throws Exception {
        String whereCondition = generateWhereCondition(datumOd,datumDo);
        List<IEntity> entites = this.repository.findAllCustom(entity, whereCondition);
        return entites
                .stream()
                .map(_entity -> (ProdajnaStavka) _entity)
                .collect(Collectors.toList());
    }
    
    private String generateWhereCondition(Date datumOd, Date datumDo) {
        StringBuilder builder = new StringBuilder("DatumProdaje");
        builder.append(" < " + "'" + prepareDate(datumDo) + "'");
        builder.append(" AND ");
        builder.append(" > " + "'" + prepareDate(datumOd) + "'");
        return builder.toString();
    }

    private String prepareDate(Date date) {
        String s = "'" + simpleDateFormat.format(date) + "'";
        return s;
    }
    
}
