package com.training.services;

import com.training.models.Load;

import java.util.List;

/**
 * Interface that extends BaseService and defines methods for operations with loads.
 *
 * @see BaseService
 * @see Load
 */
public interface LoadService extends BaseService<Load, Long> {

    /**
     * Deletes vehicle from load. Used when completing an order.
     *
     * @param id Long object - id of load to be updated
     */
    void deleteVehicleFromLoad(Long id);

    /**
     * Finds all loads.
     *
     * @return List of Load objects
     */
    List<Load> findAll();
}
