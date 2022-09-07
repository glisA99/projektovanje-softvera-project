package threads;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public abstract class ServerThread extends Thread {
    
    protected List<ClientThread> clients = new ArrayList<>();
    protected ServerSocket serverSocket;

    public ServerThread(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
    }

    public void stopServer() throws IOException {
        serverSocket.close();
        for(ClientThread thread : clients) {
            thread.getSocket().close();
        }
    }
    
    public List<ClientThread> getClients() {
        return this.clients;
    }
    
    public void disconnectClient(ClientThread clientThread) {
        this.clients.remove(clientThread);
    }
    
}
