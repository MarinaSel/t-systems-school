package com.training.services;

import com.training.models.Load;
import com.training.services.BaseService;

import java.util.List;

/**
 * Interface for load service which describes operations for Load objects extends BaseService with CRUD operations.
 */
public interface LoadService extends BaseService<Load, Long> {

    /**
     * Method for deleting Vehicle object from load.
     * Used when we want to complete the order.
     * @param id load id
     * @return load
     */
    Load deleteVehicleFromLoad(Long id);

    /**
     * Method for finding all Load objects
     * @return list
     */
    List<Load> getAll();
}
