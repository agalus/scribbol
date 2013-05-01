package org.scribbol.dao.impl;

import org.scribbol.dao.DAO;
import org.scribbol.domain.BaseEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created with IntelliJ IDEA.
 * User: drew
 * Date: 4/23/13
 * Time: 7:05 AM
 * To change this template use File | Settings | File Templates.
 */
public class AbstractInMemoryDAO<T extends BaseEntity> implements DAO<T, String> {
    protected Class<T> clazz;

    Map<String, T> inMemoryStore;

    protected AbstractInMemoryDAO(Class<T> clazz) {
        this.clazz = clazz;
        this.inMemoryStore = new ConcurrentHashMap<String, T>();
    }

    public T findById(String id) {
        return inMemoryStore.get(id);
    }

    public List<T> findByIds(Collection<String> ids) {
        List<T> entities = new ArrayList<T>();
        for(String id: ids) {
            if(inMemoryStore.containsKey(id))
                entities.add(inMemoryStore.get(id));
        }
        return entities;
    }

    public List<T> findAll() {
        List<T> entities = new ArrayList<T>();
        entities.addAll(inMemoryStore.values());
        return entities;
    }

    public T create(T entity) {
        String id = entity.getId();
        if(inMemoryStore.containsKey(id)) {
            throw new RuntimeException(id + " already exists.");
        }
        inMemoryStore.put(id, entity);

        return entity;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public List<T> create(List<T> entities) {
        synchronized (inMemoryStore) {
            for(T entity : entities) {
                String id = entity.getId();
                if(inMemoryStore.containsKey(id))
                    throw new RuntimeException(id + " already exists.");

            }
            for(T entity : entities) {
                String id = entity.getId();
                inMemoryStore.put(id, entity);
            }
        }
        return entities;
    }

    public T update(T entity) {
        String id = entity.getId();
        if(!inMemoryStore.containsKey(id))
            throw new RuntimeException(id + " does not exist.");
        inMemoryStore.put(id, entity);
        return entity;
    }

    public void delete(T entity) {
        String id = entity.getId();
        deleteById(id);
    }

    public <K, V> Map<K, V> getMap(java.lang.String key, java.lang.String value) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void deleteById(java.lang.String entityId) {
        if(!inMemoryStore.containsKey(entityId))
            throw new RuntimeException(entityId + " does not exist.");
        inMemoryStore.remove(entityId);
    }

}