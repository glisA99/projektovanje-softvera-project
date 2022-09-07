package session;

import java.net.Socket;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public class Session {
    
    private static Session instance;
    private Socket socket;

    private Session(Socket socket) {
        this.socket = socket;
    }
    
    public Socket getSocket() {
        return this.socket;
    }

}
