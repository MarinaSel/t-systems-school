package com.training.services;

import com.training.model.entities.LoadEntity;

public interface LoadService {

    LoadEntity get(Long id);

    LoadEntity create(LoadEntity loadEntity);

    LoadEntity update(LoadEntity loadEntity);

    void delete(LoadEntity loadEntity);

}
