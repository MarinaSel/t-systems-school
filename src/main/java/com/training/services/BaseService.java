package com.training.services;

public interface BaseService<T, Long> {
    T get(Long id);

    T create(T object);

    T update(T object);

    void remove(Long id);
}
