package connection;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public interface IConnectionPool {

    Connection getConnection() throws SQLException;
    boolean releaseConnection(Connection connection);
    void shutdown() throws SQLException;
    String getUrl();
    String getUser();
    String getPassword();
    
}
