package com.training.services;

import com.training.model.entities.LocationEntity;

public interface LocationService {

    LocationEntity get(Long id);

    LocationEntity create(LocationEntity locationEntity);

    LocationEntity update(LocationEntity locationEntity);

    void delete(LocationEntity locationEntity);

}
