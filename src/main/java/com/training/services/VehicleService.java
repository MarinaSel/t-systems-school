package com.training.services;

import com.training.entities.VehicleEntity;
import com.training.models.Vehicle;

import java.util.List;

/**
 * Interface for vehicle service which describes operations for Vehicle objects extends BaseService with CRUD operations.
 */
public interface VehicleService extends BaseService<Vehicle, Long> {

    /**
     * Method returns all vehicles.
     *
     * @return list of Vehicle objects
     */
    List<Vehicle> getAll();

    /**
     * Method returns list of Vehicle objects (with status 'FREE') available for load.
     *
     * @param necessaryCapacity load weight
     * @return list of Vehicle objects
     */
    List<Vehicle> getAllFreeWithNecessaryCapacityAndDrivers(Integer necessaryCapacity);

    /**
     * Method returns Vehicle object with necessary registration number.
     *
     * @param registrationNumber vehicle registration number
     * @return vehicle
     */
    Vehicle findByRegistrationNumber(String registrationNumber);

    /**
     * Method checks set of loads and if was delivered last load,
     * sets drivers status 'FREE',
     * removes this drivers from set of drivers,
     * sets vehicle status 'FREE'.
     * Used when we complete the order.
     *
     * @param vehicleEntity checking object
     */
    void checkVehicleIfEndedDelivery(VehicleEntity vehicleEntity);

    /**
     * Method sets vehicle status "WORKING" and sets loads status 'IN_PROGRESS'.
     * Used when we begin delivery
     *
     * @param id vehicle id
     * @return vehicle
     */
    Vehicle changeVehicleStatusForBeginDelivery(Long id);
}
