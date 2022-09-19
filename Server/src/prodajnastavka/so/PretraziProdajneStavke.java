package prodajnastavka.so;

import domain.IEntity;
import domain.ProdajnaStavka;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import so.AbstractSystemOperation;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public class PretraziProdajneStavke extends AbstractSystemOperation<ProdajnaStavka>  {
    
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public PretraziProdajneStavke() throws Exception {
        super();
    }

    @Override
    protected void precondition(ProdajnaStavka entity) throws Exception {
        return;
    }

    @Override
    protected void executeOperation(ProdajnaStavka entity) throws Exception {
        // Employee can search by StavkaID, DatumProdaje, Klijent or Artikl
        // if any of those is != null it will be included in WHERE condition
        // (except Stavka ID)
        List<ProdajnaStavka> prodajneStavke = new ArrayList<>();
        String whereCondition = generateWhereCondition(entity);
        List<IEntity> entities;
        
        if (whereCondition.isEmpty()) {
            entities = this.repository.findAll(entity);
        } else {
            entities = this.repository.findAllCustom(entity, whereCondition);
        }
        
        entities.forEach(_entity -> prodajneStavke.add((ProdajnaStavka) _entity));
        this.operationResult = prodajneStavke;
    }

    private String generateWhereCondition(ProdajnaStavka entity) {
        StringBuilder builder = new StringBuilder("");
        boolean conditionAdded = false;
        
        if (entity.getProdajnaStavkaID() != null) {
            builder.append("ProdajnaStavkaID = " + entity.getProdajnaStavkaID());
            return builder.toString();
        }
        
        if (entity.getDatumProdaje() != null) {
            if (conditionAdded) builder.append(" AND ");
            builder.append("DatumProdaje = '" + simpleDateFormat.format(entity.getDatumProdaje()) + "'");
            conditionAdded = true;
        }
        
        if (entity.getKlijentID() != null) {
            if (conditionAdded) builder.append(" AND ");
            builder.append("KlijentID = " + entity.getKlijentID());
            conditionAdded = true;
        }
        
        if (entity.getSifraArtikla() != null) {
            if (conditionAdded) builder.append(" AND ");
            builder.append("SifraArtikla = " + entity.getSifraArtikla());
            conditionAdded = true;
        }
        
        return builder.toString();
    }
    
}
