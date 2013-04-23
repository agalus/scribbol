package org.scribbol.service;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: drew
 * Date: 4/23/13
 * Time: 6:51 AM
 * To change this template use File | Settings | File Templates.
 */
public interface CrudService<T, S extends Serializable> {

    /**
     * Loads an entity from the database
     *
     * @param id the id of the entity
     * @return the object
     */
    public T get(S id);

    /**
     * Loads all entities from the database
     *
     * @return list of all entities
     */
    public List<T> getAll();

    /**
     * Saves the entity to the database
     *
     * @param entity the entity to save
     */
    public T create(T entity);
    /**
     * Saves entities to the database
     *
     * @param entities the entities to save
     */
    List<T> create(List<T> entities);

    /**
     * Updates the entity in the database
     *
     * @param entity the entity to update
     */
    public T update(T entity);

    /**
     * Deep-deletes an entity.
     *
     * @param entity the entity to delete
     */
    public void delete(T entity);

    /**
     * Deletes an entity by its id.
     *
     * @param entityId the id of the entity
     */
    public void deleteById(S entityId);

    public List<String> validate(T entity);
}
