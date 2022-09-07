package session;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public class ServerProperties {
    
    private String host;
    private String port;

    public ServerProperties() {
    }

    public ServerProperties(String host, String port) {
        this.host = host;
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
    
    
    

}
