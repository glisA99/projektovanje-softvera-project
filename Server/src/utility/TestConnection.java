package utility;

import connection.*;
import configuration.DbAccessParams;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public class TestConnection {

    public static boolean testConnection(DbAccessParams params) {
        try {
            String url = params.getUrl();
            String password = params.getPassword();
            String username = params.getUser();
            DriverManager.getConnection(url + "?serverTimezone=UTC", username, password);
        } catch (SQLException ex) {
            Logger.getLogger(TestConnection.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
}
