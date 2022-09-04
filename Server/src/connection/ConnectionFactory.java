/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package connection;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public class ConnectionFactory implements IConnectionFactory {
    
    private IConnectionPool connectionPool;
    private static ConnectionFactory instance;
    
    private ConnectionFactory() {
        
    }
    
    public static ConnectionFactory getInstance() {
        if (instance == null) {
            connectionPool = ConnectionPool.create(url, user, password);
        } 
        return instance;
    }

    @Override
    public Connection getConnection() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean releaseConnection(Connection connection) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void shutdown() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
