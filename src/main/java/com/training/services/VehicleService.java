package com.training.services;

import com.training.entities.VehicleEntity;
import com.training.models.Vehicle;

import java.util.List;

/**
 * Interface that extends BaseService and defines methods for operations with vehicles.
 *
 * @see BaseService
 * @see Vehicle
 */
public interface VehicleService extends BaseService<Vehicle, Long> {

    /**
     * Finds all vehicles.
     *
     * @return List of Vehicle objects
     */
    List<Vehicle> getAll();

    /**
     * Finds vehicles with status 'FREE'.
     *
     * @param necessaryCapacity Integer object with capacity for search
     * @return list of Vehicle objects
     */
    List<Vehicle> getAllFreeWithNecessaryCapacity(Integer necessaryCapacity);

    /**
     * Finds vehicle by registration number.
     *
     * @param registrationNumber String object with registration number for search
     * @return Vehicle object with specified registration number
     */
    Vehicle findByRegistrationNumber(String registrationNumber);

    /**
     * Checks loads of vehicle and if all loads have been delivered
     * then sets drivers statuses to 'FREE' abd removes them from vehicle,
     * sets vehicle status 'FREE'. Used when completing an order.
     *
     * @param vehicleEntity VehicleEntity object to be checked
     */
    void checkIfCompletedDelivery(VehicleEntity vehicleEntity);

    /**
     * Sets vehicle status to "WORKING" and sets all its loads statuses
     * to 'IN_PROGRESS'. Used when starting delivery.
     *
     * @param id Long object - id of vehicle to be updated
     */
    void changeStatusWhenStartingDelivery(Long id);
}
