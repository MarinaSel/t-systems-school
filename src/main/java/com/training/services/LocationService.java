package com.training.services;

import com.training.entities.LocationEntity;
import com.training.models.Location;

import java.util.List;

public interface LocationService extends BaseService<LocationEntity, Long> {


    /**
     * Finds all drivers.
     *
     * @return List of all found Driver objects
     */
    List<Location> findAll();

    /**
     * Finds location by name.
     *
     * @param name String object with name for search
     * @return Location object with specified name
     */
    Location findByName(String name);
}
