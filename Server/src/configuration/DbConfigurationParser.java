package configuration;

import static configuration.ConfigurationConstants.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.Properties;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public class DbConfigurationParser implements IConfigurationParser<DbAccessParams> {
    
    private static DbConfigurationParser instance;
    private Properties properties;
    
    private DbConfigurationParser() {
        this.properties = new Properties();
    }
    
    public static DbConfigurationParser getInstance() {
        if (instance == null) {
            instance = new DbConfigurationParser();
        }
        return instance;
    }
    
    @Override
    public DbAccessParams parseConfiguration() throws Exception {
        // load configuration
        properties.load(new FileInputStream(this.assembleConfigurationPath()));
        
        DbAccessParams dbAccessParams = new DbAccessParams();
        String dbtype = properties.getProperty(Db.RDBMS_TYPE);
        String host = properties.getProperty(Db.HOST);
        String port = properties.getProperty(Db.PORT);
        String dbname = properties.getProperty(Db.DB_NAME);
        String username = properties.getProperty(Db.USERNAME);
        String password = properties.getProperty(Db.PASSWORD);
        dbAccessParams.setUser(username);
        dbAccessParams.setPassword(password);
        dbAccessParams.setUrl(this.assembleUrl(host, port, dbtype, dbname));
        
        return dbAccessParams;
    }

    @Override
    public void writeConfiguration(DbAccessParams config) throws Exception {
        // load configuration
        properties.load(new FileInputStream(this.assembleConfigurationPath()));
        
        properties.setProperty(Db.USERNAME, config.getUser());
        properties.setProperty(Db.PASSWORD, config.getPassword());
        String url = config.getUrl();
        properties.setProperty(Db.DB_NAME, getFromUrl(url, Db.DB_NAME));
        properties.setProperty(Db.PORT, getFromUrl(url, Db.PORT));
        properties.setProperty(Db.HOST, getFromUrl(url, Db.HOST));
        properties.setProperty(Db.RDBMS_TYPE, getFromUrl(url, Db.RDBMS_TYPE));
        
        FileOutputStream fileOutputStream = new FileOutputStream(assembleConfigurationPath());
        properties.store(fileOutputStream, "Date updated: " + new Date().toString());
    }
    
    private String assembleConfigurationPath() {
        String path = CONFIGURATION_PATH + DB_CONFIG_NAME;
        return path;
    }
    
    public static String assembleUrl(String host,String port,String dbtype,String dbname) {
        String url = "jdbc:" + dbtype + "://" + host + ":" + port + "/" + dbname;
        return url;
    }
    
    public static String getFromUrl(String url,String type) throws Exception {
        // example: jdbc:mysql://localhost:3306/butikdb
        String[] parts = url.split(":");
        String rdmbsType = parts[1];
        String host = parts[2].substring(2);
        String[] portNameSplit = parts[3].split("/");
        String port = portNameSplit[0];
        String dbName = portNameSplit[1];
        switch(type) {
            case Db.DB_NAME: return dbName;
            case Db.HOST: return host;
            case Db.PORT: return port;
            case Db.RDBMS_TYPE: return rdmbsType;
            default: throw new Exception("Invalid url data type");
        }
    }
    
}
