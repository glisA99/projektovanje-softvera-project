package connection;

import java.sql.Connection;
import java.sql.SQLException;
import configuration.DbConfigurationParser;
import configuration.DbAccessParams;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public class ConnectionFactory implements IConnectionFactory {
    
    private IConnectionPool connectionPool;
    private static ConnectionFactory instance;
    
    private ConnectionFactory() throws Exception {
        DbConfigurationParser parser = DbConfigurationParser.getInstance();
        DbAccessParams params = parser.parseConfiguration();
        String url = params.getUrl();
        String user = params.getUser();
        String password = params.getPassword();
        IConnectionPool pool = ConnectionPool.create(url, user, password);
        this.connectionPool = pool;
    }
    
    public static ConnectionFactory getInstance() throws Exception {
        if (instance == null) {
            instance = new ConnectionFactory();
        } 
        return instance;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return this.connectionPool.getConnection();
    }

    @Override
    public boolean releaseConnection(Connection connection) throws SQLException {
        return this.connectionPool.releaseConnection(connection);
    }

    @Override
    public void shutdown() throws SQLException {
        this.connectionPool.shutdown();
        // SET INSTANCE AS NULL TO BE RECREATED ON NEXT SERVER START
        instance = null;
    }

    
    
}
