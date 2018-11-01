package com.training.services;

/**
 * Base interface which describes base CRUD operations.
 *
 * @param <T>
 * @param <Long>
 */
public interface BaseService<T, Long> {
    T get(Long id);

    void save(T object);

    void remove(Long id);
}
