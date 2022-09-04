/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package connection;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public interface IConnectionFactory {
    
    Connection getConnection() throws SQLException;
    boolean releaseConnection(Connection connection);
    void shutdown() throws SQLException;
    
}
