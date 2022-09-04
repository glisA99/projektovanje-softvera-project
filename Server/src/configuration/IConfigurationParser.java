package configuration;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public interface IConfigurationParser<T> {
    
    public T parseConfiguration() throws Exception;
    public void writeConfiguration(T config) throws Exception;
    
}
