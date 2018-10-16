package com.training.services;

public interface BaseService<T, Long> {
    T get(Long id);

    T save(T object);

    void remove(Long id);
}
