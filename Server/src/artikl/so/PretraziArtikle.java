package artikl.so;

import domain.Artikl;
import domain.IEntity;
import java.util.ArrayList;
import java.util.List;
import so.AbstractSystemOperation;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public class PretraziArtikle extends AbstractSystemOperation<Artikl> {

    public PretraziArtikle() throws Exception {
        super();
    }
    
    @Override
    protected void precondition(Artikl entity) throws Exception {
        // no precondition
        return;
    }

    @Override
    protected void executeOperation(Artikl entity) throws Exception {
        // Employee can search by ID, Name/Title or Suplier name
        // if any of those is != null it will be included in WHERE condition
        List<Artikl> artikli = new ArrayList<>();
        String whereCondition = generateWhereCondition(entity);
        List<IEntity> entities;
        
        if (whereCondition.isEmpty()) {
            entities = this.repository.findAll(entity);
        } else {
            entities = this.repository.findAllCustom(entity,whereCondition);
        }
        
        entities.forEach(_entity -> artikli.add((Artikl) _entity));
        this.operationResult = artikli;
    }

    private String generateWhereCondition(Artikl entity) {
        StringBuilder builder = new StringBuilder("");
        String likeCondition = "";
        boolean conditionAdded = false;
        
        // condition for sifra artikla
        if (entity.getSifraArtikla() != null) {
            builder.append("SifraArtikla = " + entity.getSifraArtikla());
            return builder.toString();
        }
        
        // condition for naziv
        if (entity.getNaziv() != null) {
            if (conditionAdded) builder.append(" AND ");
            likeCondition = constructLikeCondition(entity.getNaziv());
            builder.append("Naziv LIKE ").append(likeCondition);
            conditionAdded = true;
        }
        
        // condition for proizvodjac
        if (entity.getProizvodjac() != null) {
            if (conditionAdded) builder.append(" AND ");
            likeCondition = constructLikeCondition(entity.getProizvodjac());
            builder.append("Proizvodjac LIKE ").append(likeCondition);
            conditionAdded = true;
        }
        
        return builder.toString();
    }
    
    private String constructLikeCondition(String value) {
        return "'%" + value + "%'";
    }
    
}
