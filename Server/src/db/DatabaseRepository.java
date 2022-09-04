package db;

import domain.IEntity;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public class DatabaseRepository {
    
    private Connection connection;

    // DepInject connection throught constructor  
    public DatabaseRepository(Connection connection) {
        this.connection = connection;
    }

    // Save entity
    public IEntity save(IEntity entity) throws Exception {
        StringBuilder builder = new StringBuilder();
        builder.append("INSERT INTO ")
                .append(entity.getTableName())
                .append(" (")
                .append(entity.getColumnNamesForInsert())
                .append(" ) ")
                .append("VALUES")
                .append(" (")
                .append(entity.getColumnValuesForInsert())
                .append(")");
        String query = builder.toString();
        
        Statement statement = connection.createStatement();
        if (entity.isIdAutoincrement()) {
            statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = statement.getGeneratedKeys();
            if (!rs.next()) throw new Exception("Entity not saved");
            Long generatedKey = rs.getLong(1);
            entity.setAutoincrementId(generatedKey);
        } else statement.executeUpdate(query);
        
        return this.findByID(entity);
    }
    
    // Find entity by its identificator
    public IEntity findByID(IEntity entity) throws Exception {
        String query = "SELECT * FROM " + entity.getTableName() + " WHERE " + entity.getWhereCondition();
        Statement statement = connection.createStatement();
        
        ResultSet rs = statement.executeQuery(query);
        
        if (!rs.next()) return null;
        return entity.getNewRecord(rs);
    }
    
    // Find all entities 
    public List<IEntity> findAll(IEntity entity) throws Exception {
        String query = "SELECT * FROM " + entity.getTableName();
        Statement statement = connection.createStatement();
        
        ResultSet rs = statement.executeQuery(query);
        
        List<IEntity> entities = new ArrayList<>();
        while (rs.next()) {
            IEntity entity1 = entity.getNewRecord(rs);
            entities.add(entity1);
        }
        
        return entities;
    }
    
    // Find all entities with custom WHERE clause appended to query
    public List<IEntity> findAllCustom(IEntity entity,String whereCondition) throws Exception {
        String query = "SELECT * FROM " + entity.getTableName() + " WHERE " + whereCondition;
        Statement statement = connection.createStatement();
        
        ResultSet rs = statement.executeQuery(query);
        
        List<IEntity> entities = new ArrayList<>();
        while (rs.next()) {
            IEntity entity1 = entity.getNewRecord(rs);
            entities.add(entity1);
        }
        
        return entities;
    }
    
    // Update entity
    public boolean update(IEntity entity) throws Exception {
        StringBuilder builder = new StringBuilder();
        builder.append("UPDATE ")
                .append(entity.getTableName())
                .append(" SET ")
                .append(entity.getColumnValuesForUpdate())
                .append(" WHERE ")
                .append(entity.getWhereCondition());
        String query = builder.toString();
        
        Statement statement = connection.createStatement();
        int res = statement.executeUpdate(query);
        return res > 0;
    }
    
    // Delete entity
    public boolean delete(IEntity entity) throws Exception {
        String query = "DELETE FROM " + entity.getTableName() + " WHERE " + entity.getWhereCondition();
        Statement statement = connection.createStatement();
        int res = statement.executeUpdate(query);
        return res > 0;
    }
    
}
