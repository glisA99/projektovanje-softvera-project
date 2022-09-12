package klijent.so;

import domain.IEntity;
import domain.Klijent;
import java.util.ArrayList;
import java.util.List;
import so.AbstractSystemOperation;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public class PronadjiKlijente extends AbstractSystemOperation<Klijent> {

    public PronadjiKlijente() throws Exception {
        super();
    }

    @Override
    protected void precondition(Klijent param) throws Exception {
        // no precondition
        return;
    }

    @Override
    protected void executeOperation(Klijent entity) throws Exception {
        List<Klijent> clients = new ArrayList<>();
        // Clients can be searched by part of any attributes: ID, Ime, Prezime, Email
        // If any of those is != null, it will be included in where condition
        String whereCondition = generateWhereCondition(entity);
        List<IEntity> entities;

       
        if (whereCondition.isEmpty()) {
             // if string is empty -> return all clients
            entities = this.repository.findAll(entity);
        } else {
            // if string is not empty -> return all clients that satisfy conditions
            entities = this.repository.findAllCustom(entity, whereCondition);
        }

        entities.forEach(_entity -> clients.add((Klijent) _entity));
        this.operationResult = clients;
    }

    private String generateWhereCondition(Klijent entity) {
        StringBuilder builder = new StringBuilder("");
        String likeCondition = "";
        boolean conditionAdded = false;

        // condition for KlijentID
        if (entity.getKlijentID() != null) {
            builder.append("KlijentID = " + entity.getKlijentID());
            return builder.toString();
        }

        // condition for Ime
        if (entity.getIme() != null) {
            if (conditionAdded) builder.append(" AND ");
            likeCondition = constructLikeCondition(entity.getIme());
            builder.append("Ime LIKE ").append(likeCondition);
            conditionAdded = true;
        }

        // condition for Prezime
        if (entity.getPrezime() != null) {
            if (conditionAdded) builder.append(" AND ");
            likeCondition = constructLikeCondition(entity.getPrezime());
            builder.append("Prezime LIKE ").append(likeCondition);
            conditionAdded = true;
        }

        // condition for Email
        if (entity.getEmail() != null) {
            if (conditionAdded) builder.append(" AND ");
            likeCondition = constructLikeCondition(entity.getEmail());
            builder.append("Email LIKE ").append(likeCondition);
            conditionAdded = true;
        }

        return builder.toString();
    }

    private String constructLikeCondition(String value) {
        return "'%" + value + "%'";
    }

}
