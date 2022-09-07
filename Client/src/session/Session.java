package session;

import domain.Radnik;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public class Session {
    
    private static Session instance;
    private Socket socket;
    private Radnik loggedRadnik;

    private Session() throws IOException {
        ConfigurationParser parser = ConfigurationParser.getInstance();
        ServerProperties props = parser.parseConfiguration();
        this.socket = new Socket(props.getHost(),Integer.parseInt(props.getPort()));
        System.out.println("Konekcija sa serverom uspesno uspostavljena...");
    }
    
    public static Session getInstance() throws IOException {
        if (instance == null || instance.socket.isClosed()) {
            instance = new Session();
        }
        return instance;
    }
    
    public Socket getSocket() {
        return this.socket;
    }

    public Radnik getLoggedRadnik() {
        return loggedRadnik;
    }

    public void setLoggedRadnik(Radnik loggedRadnik) {
        this.loggedRadnik = loggedRadnik;
    }
    
    

}
