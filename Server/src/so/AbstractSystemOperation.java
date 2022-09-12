package so;

import connection.ConnectionFactory;
import db.DatabaseRepository;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public abstract class AbstractSystemOperation<T> {
    
    protected DatabaseRepository repository;
    protected Connection connection;
    public Object operationResult;

    public AbstractSystemOperation() throws Exception {
        Connection _connection = ConnectionFactory.getInstance().getConnection();
        this.repository = new DatabaseRepository(_connection);
        this.connection = _connection;
    }

    // Template method for executing system operation
    public final void execute(T entity) throws Exception {
        try {
            precondition(entity);
            startTransaction();
            executeOperation(entity);
            commitTransaction();
        } catch (Exception ex) {
            ex.printStackTrace();
            rollbackTransaction();
            throw ex;
        }
    }

    
    protected abstract void precondition(T entity)throws Exception;
    protected abstract void executeOperation(T entity) throws Exception;
    

    private void startTransaction() throws SQLException {
        connection.setAutoCommit(false);
    }

    private void commitTransaction() throws SQLException {
        connection.commit();
    }

    private void rollbackTransaction() throws SQLException {
        connection.rollback();
    }

}
