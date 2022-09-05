package communication;

import java.io.BufferedInputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public class Receiver {
    
    private final Socket socket;

    public Receiver(Socket socket) {
        this.socket = socket;
    }
    
    public Object receive() throws Exception {
        try {
            ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
            return in.readObject();
        } catch (Exception ex) {
            Logger.getLogger(Receiver.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Error receiving object: " + ex.getMessage());
        }
    }

}
