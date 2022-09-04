/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package configuration;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public class ConfigurationConstants {

    public static final String CONFIGURATION_PATH = "configuration/";
    public static final String SERVER_CONFIG_NAME = "server.properties";
    public static final String DB_CONFIG_NAME = "dbconfig.properties";
    
    public static class Db {
        public static final String RDBMS_TYPE = "RDBMS_TYPE";
        public static final String HOST = "HOST";
        public static final String PORT = "PORT";
        public static final String DB_NAME = "DB_NAME";
        public static final String USERNAME = "USERNAME";
        public static final String PASSWORD = "PASSWORD";
    }
    
    public static class Server {
        public static final String PORT = "PORT";
    }
    
}
