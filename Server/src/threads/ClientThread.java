package threads;

import java.net.Socket;
import utility.ClientStatistic;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public abstract class ClientThread extends Thread {
    
    protected Socket socket;
    protected ClientStatistic clientStatistic;

    protected ClientThread(Socket socket) {
        this.socket = socket;
        this.clientStatistic = new ClientStatistic();
    }
    
    public Socket getSocket() {
        return this.socket;
    }
    
    public ClientStatistic getStatistic() {
        return this.clientStatistic;
    }
    
}
