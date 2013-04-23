package org.scribbol.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: drew
 * Date: 4/15/13
 * Time: 5:04 AM
 * To change this template use File | Settings | File Templates.
 */
public interface DAO<T, S> extends Serializable {

    public T findById(S id);

    public List<T> findByIds(final Collection<S> ids);

    public List< T > findAll();

    public T create(T entity);

    public List<T> create(List<T> entities);

    public T update(T entity);

    public void delete(T entity);

    public void deleteById(S entityId);

    /**
     * Returns a map of key->value for all entities
     */
    public <K, V> Map<K, V> getMap(String key, String value);

}
