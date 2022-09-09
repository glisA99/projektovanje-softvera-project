package session;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public class ConfigurationParser {
    
    public static String CONFIGURATION_PATH = "configuration/server.properties";
    private static ConfigurationParser instance;
    
    private ConfigurationParser() {}
    
    public static ConfigurationParser getInstance() {
        if (instance == null) {
            instance = new ConfigurationParser();
        }
        return instance;
    }
    
    public ServerProperties parseConfiguration() throws FileNotFoundException, IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(CONFIGURATION_PATH));
        
        ServerProperties serverProperties = new ServerProperties();
        serverProperties.setHost((String) properties.get("HOST"));
        serverProperties.setPort((String) properties.get("PORT"));
        
        return serverProperties;
    }
    
    public void writeConfiguration(ServerProperties serverProperties) throws FileNotFoundException, IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(CONFIGURATION_PATH));
        
        properties.setProperty(constants.Constants.HOST, serverProperties.getHost());
        properties.setProperty(constants.Constants.PORT, serverProperties.getPort());
        
        FileOutputStream fileOutputStream = new FileOutputStream(CONFIGURATION_PATH);
        properties.store(fileOutputStream, "Date updated: " + new Date().toString());
    }

}
