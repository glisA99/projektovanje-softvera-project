package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public class ConnectionPool implements IConnectionPool {
    
    private String url;
    private String user;
    private String password;
    private List<Connection> connectionPool;
    private List<Connection> usedConnections = new ArrayList<>();
    public static int INITIAL_POOL_SIZE = 10;
    public static int MAX_POOL_SIZE = 30;
    
    public ConnectionPool(String url, String user, String password, List<Connection> connectionPool) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.connectionPool = connectionPool;
    }
    
    public static ConnectionPool create(String url,String user, String password) throws SQLException {
        List<Connection> pool = new ArrayList<>(INITIAL_POOL_SIZE);
        for(int i = 0;i < INITIAL_POOL_SIZE;i++) {
            pool.add(createConnection(url, user, password));
        }
        return new ConnectionPool(url,user,password,pool);
    }
    
    private static Connection createConnection(String url,String user, String password) throws SQLException {
        Connection connection =  DriverManager.getConnection(url,user, password);
        connection.setAutoCommit(false);
        return connection;
    }

    @Override
    public Connection getConnection() throws SQLException {
        if (connectionPool.isEmpty()) {
            if (usedConnections.size() < MAX_POOL_SIZE) {
                this.connectionPool.add(createConnection(url, user, password));
            } else {
                throw new RuntimeException("Maximum pool size reached, no available connections!");
            }
        }
        
        int removeIndex = this.connectionPool.size() - 1;
        Connection connection = connectionPool.remove(removeIndex);
        
        if (connection.isClosed()) {
            connection = createConnection(url, user, password);
        }
        
        usedConnections.add(connection);
        return connection;
    }

    @Override
    public boolean releaseConnection(Connection connection) {
        connectionPool.add(connection);
        return usedConnections.remove(connection);
    }
    
    @Override
    public void shutdown() throws SQLException {
        // close all from used connections list
        usedConnections.forEach(this::releaseConnection);
        // close all from connection pool
        for(Connection connection : connectionPool) {
            connection.close();
        }
        connectionPool.clear();
    }
    
    public int getSize() {
        return this.connectionPool.size() + usedConnections.size();
    }

    @Override
    public String getUrl() {
        return this.url;
    }

    @Override
    public String getUser() {
        return this.user;
    }

    @Override
    public String getPassword() {
        return this.password;
    }
    
}
