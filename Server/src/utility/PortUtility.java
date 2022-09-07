package utility;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public class PortUtility {

    private static int MIN_PORT_NUMBER = 1;
    private static int MAX_PORT_NUMBER = 65535;

    public static boolean isPortAvailable(int port) {
        if (port < MIN_PORT_NUMBER || port > MAX_PORT_NUMBER) {
            throw new IllegalArgumentException("Invalid start port: " + port);
        }

        ServerSocket ss = null;
        DatagramSocket ds = null;
        try {
            ss = new ServerSocket(port);
            ss.setReuseAddress(true);
            ds = new DatagramSocket(port);
            ds.setReuseAddress(true);
            return true;
        } catch (IOException e) {
        } finally {
            if (ds != null) {
                ds.close();
            }

            if (ss != null) {
                try {
                    ss.close();
                } catch (IOException e) {
                    /* should not be thrown */
                }
            }
        }

        return false;
    }

}
