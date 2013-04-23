package org.scribbol.service.impl;

import org.scribbol.dao.DAO;
import org.scribbol.service.CrudService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: drew
 * Date: 4/23/13
 * Time: 6:54 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractCrudService<T, S extends Serializable> implements CrudService<T, S> {
    protected Class<T> clazz;

    /**
     * Returns the DAO for this service
     *
     * @return the DAO for this service
     */
    protected abstract DAO<T,S> getDAO();

    protected AbstractCrudService(Class<T> clazz) {
        this.clazz = clazz;
    }

    public T get(S id) {
        return getDAO().findById(id);
    }

    public List<T> getAll() {
        return getDAO().findAll();
    }

    public T create(T entity) {
        // validate
        List<String> errors = validate(entity);
        if (!errors.isEmpty()) {
            throw new RuntimeException(errors.toString());
        }

        // create
        T e = getDAO().create(entity);

        // return persisted entity
        return e;
    }

    public List<T> create(List<T> entities) {
        List<String> errors = new ArrayList<String>();
        for (T entity : entities) {
            errors.addAll(validate(entity));
        }
        if (!errors.isEmpty()) {
            throw new RuntimeException(errors.toString());
        }

        List<T> list = new ArrayList<T>();
        for (T entity: entities) {
            list.add(create(entity));
        }
        return list;
    }

    public T update(T entity) {
        // validate
        List<String> errors = validate(entity);
        if (!errors.isEmpty()) {
            throw new RuntimeException(errors.toString());
        }

        // update
        T e = getDAO().update(entity);

        // return persisted entity
        return e;
    }

    public void delete(T entity) {
        getDAO().delete(entity);
    }

    public void deleteById(S entityId) {
        T entity = get(entityId);
        if (entity == null) return;
        delete(entity);
    }

    public List<String> validate(T entity) {
        return new ArrayList<String>();
    }






}
