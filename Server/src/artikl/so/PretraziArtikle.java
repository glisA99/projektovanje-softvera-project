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
            entities = this.repository.findAllCustom(entity, whereCondition);
        } else {
            entities = this.repository.findAll(entity);
        }
        
        entities.forEach(_entity -> artikli.add((Artikl) _entity));
        this.operationResult = artikli;
    }

    private String generateWhereCondition(Artikl entity) {
        StringBuilder builder = new StringBuilder("");
        String likeCondition = "";
        
        // condition for sifra artikla
        if (entity.getSifraArtikla() != null) {
            likeCondition = constructLikeCondition(entity.getSifraArtikla().toString());
            builder.append("CAST(SifraArtikla as CHAR) LIKE ").append(likeCondition);
        }
        
        // condition for naziv
        if (entity.getNaziv() != null) {
            likeCondition = constructLikeCondition(entity.getNaziv());
            builder.append(" AND ").append("Naziv LIKE ").append(likeCondition);
        }
        
        // condition for proizvodjac
        if (entity.getProizvodjac() != null) {
            likeCondition = constructLikeCondition(entity.getProizvodjac());
            builder.append(" AND ").append("Proizvodjac LIKE ").append(likeCondition);
        }
        
        return builder.toString();
    }
    
    private String constructLikeCondition(String value) {
        return "'%" + value + "%'";
    }
    
}
