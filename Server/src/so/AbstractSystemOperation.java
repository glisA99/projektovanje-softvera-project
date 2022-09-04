package so;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public abstract class AbstractSystemOperation<T> {

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
    protected abstract void executeOperation(T param);

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
