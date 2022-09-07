package threads;

import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public class ServerThreadImpl extends ServerThread {

    public ServerThreadImpl(int port) throws IOException {
        super(port);
    }

    @Override
    public void run() {
        try {
            while (!serverSocket.isClosed() && !isInterrupted()) {
                System.out.println("Waiting for a client to connect...");
                Socket socket = serverSocket.accept();
                System.out.println("New client connected with server...");
                
                ClientThread clientThread = new ClientThreadImpl(socket, this);
                clientThread.start();
                this.clients.add(clientThread);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("Server shutdown...");
        }
    }

}
