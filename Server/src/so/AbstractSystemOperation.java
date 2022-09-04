package so;

import db.DatabaseRepository;
import java.sql.Connection;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public abstract class AbstractSystemOperation<T> {
    
    protected DatabaseRepository repository;
    protected Connection connection;

    public AbstractSystemOperation() {
//        this.repository = new DatabaseRepository
    }

    // Template method for executing system operation
    public final void execute(T param) throws Exception {
        try {
            precondition();
            startTransaction();
            executeOperation(param);
            commitTransaction();
        } catch (Exception ex) {
            rollbackTransaction();
        }
    }

    protected abstract void precondition()throws Exception;
    protected abstract void executeOperation(T param) throws Exception;

    private void startTransaction() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void commitTransaction() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void rollbackTransaction() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
