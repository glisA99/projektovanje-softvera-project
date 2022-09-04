package configuration;

import static configuration.ConfigurationConstants.*;
import java.io.FileInputStream;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private String assembleConfigurationPath() {
        String path = CONFIGURATION_PATH + DB_CONFIG_NAME;
        return path;
    }
    
    private String assembleUrl(String host,String port,String dbtype,String dbname) {
        String url = "jdbc:" + dbtype + "://" + host + ":" + port + "/" + dbname;
        return url;
    }
    
}
