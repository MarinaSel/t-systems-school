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
     * Deletes vehicle from load and marks load as done. Used when completing an order.
     *
     * @param id Long object - id of load to be delivered
     */
    void deliverLoad(Long id);

    /**
     * Finds all loads.
     *
     * @return List of Load objects
     */
    List<Load> findAll();

    /**
     * Assigns load to vehicle, vehicle to drivers and marks them as assigned.
     * Used when saving load assigned to vehicle.
     *
     * @param load                 Load object to be assigned
     * @param registrationNumber   String object - registration number of vehicle
     * @param primaryDriverLicense String object - primary driver license number
     * @param coDriverLicense      String object - co-driver license number
     * @param pickUpLocationId     Long object - pick up location id
     * @param deliveryLocationId   Long object - delivery location id
     */
    void saveAssignedLoad(Load load, String registrationNumber, String primaryDriverLicense, String coDriverLicense,
                          Long pickUpLocationId, Long deliveryLocationId);

    /**
     * Finds all done loads.
     *
     * @return List of Load objects
     */
    List<Load> findDoneLoads();

    void setHistory(Long loadId);
}
