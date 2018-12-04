package com.training.services;

/**
 * Interface that defines base CRUD operations with objects.
 *
 * @param <T> type of object to be managed
 * @param <Long> type of id of managed object
 */
public interface BaseService<T, Long> {

    /**
     * Finds object by id.
     *
     * @param id id for search
     * @return found object
     */
    T find(Long id);

    /**
     * Creates new object or updates existing one.
     *
     * @param object object to be created or updated
     */
    void save(T object);
}
