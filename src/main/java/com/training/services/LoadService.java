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
     */
    void saveAssignedLoad(Load load, String registrationNumber, String primaryDriverLicense, String coDriverLicense,
                          String pickUpLocationName, String deliveryLocationName);

    /**
     * Assigns load to locations.
     * Used when saving load without vehicle and drivers.
     *
     * @param load
     * @param pickUpLocationName
     * @param deliveryLocationName
     */
    void saveAssignedToLocationLoad(Load load, String pickUpLocationName, String deliveryLocationName);
}
