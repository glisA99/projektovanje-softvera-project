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
    protected ServerThread serverThread;

    protected ClientThread(Socket socket, ServerThread serverThread) {
        this.socket = socket;
        this.clientStatistic = new ClientStatistic();
        this.serverThread = serverThread;
    }
    
    public Socket getSocket() {
        return this.socket;
    }
    
    public ClientStatistic getStatistic() {
        return this.clientStatistic;
    }
    
}
