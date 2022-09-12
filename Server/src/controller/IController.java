package controller;

import java.util.List;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public interface IController<T> {

    public T save(T t) throws Exception;
    public T create() throws Exception;
    public void delete(T t) throws Exception;
    public List<T> findAll() throws Exception;
    public List<T> findAllCustom(T t) throws Exception;
    public T findOne(T t) throws Exception;
    
}
