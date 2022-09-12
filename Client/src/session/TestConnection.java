package session;

import java.net.Socket;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public class TestConnection {

     public static boolean testConnection(ServerProperties properties) {
        try {
            Socket socket = new Socket(properties.getHost(),Integer.parseInt(properties.getPort()));
            socket.close();
        } catch (Exception ex) {
            System.out.println("Connection can NOT be established...");
            return false;
        }
        return true;
     }
    
}
