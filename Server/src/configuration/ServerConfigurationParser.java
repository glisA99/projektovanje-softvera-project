package configuration;

import java.util.Properties;
import static configuration.ConfigurationConstants.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public class ServerConfigurationParser implements IConfigurationParser<ServerInitParams> {

    private static ServerConfigurationParser instance;
    private Properties properties;
    
    private ServerConfigurationParser() {
        this.properties = new Properties();
    }
    
    public static ServerConfigurationParser getInstance() {
        if (instance == null) {
            instance = new ServerConfigurationParser();
        }
        return instance;
    }
    
    @Override
    public ServerInitParams parseConfiguration() throws Exception {
        String path = CONFIGURATION_PATH + SERVER_CONFIG_NAME;
        properties.load(new FileInputStream(path));
        
        ServerInitParams params = new ServerInitParams();
        params.setPort(Integer.parseInt(properties.getProperty("PORT")));
        
        return params;
    }

    @Override
    public void writeConfiguration(ServerInitParams config) throws Exception {
        String port = String.valueOf(config.getPort());
        String path = CONFIGURATION_PATH + SERVER_CONFIG_NAME;
        properties.load(new FileInputStream(path));
        
        properties.setProperty(Server.PORT, port);
        
        FileOutputStream fileOutputStream = new FileOutputStream(path);
        properties.store(fileOutputStream, "Date updated: " + new Date().toString());
    }

}
