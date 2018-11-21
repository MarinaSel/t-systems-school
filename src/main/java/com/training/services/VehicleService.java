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
    List<Vehicle> findAll();

    /**
     * Finds free vehicles.
     *
     * @param necessaryCapacity Integer object - capacity for search
     * @return list of Vehicle objects
     */
    List<Vehicle> findAllFreeWithNecessaryCapacity(Integer necessaryCapacity);

    /**
     * Finds vehicle by registration number.
     *
     * @param registrationNumber String object - registration number for search
     * @return Vehicle object with specified registration number
     */
    Vehicle findByRegistrationNumber(String registrationNumber);

    /**
     * Marks drivers assigned to vehicle as free and removes them from
     * vehicle, marks vehicle as free. Used when completing or rejecting
     * delivery or assigning new vehicle to load.
     *
     * @param vehicleEntity VehicleEntity object to be freed
     */
    void freeVehicleAndDrivers(VehicleEntity vehicleEntity);

    /**
     * Marks vehicle as working and marks all its loads as
     * being in progress. Used when starting delivery.
     *
     * @param id Long object - id of vehicle to be updated
     */
    void startDelivery(Long id);

    /**
     * Assigns vehicle to driver(s). Used when saving load assigned to vehicle.
     *
     * @param vehicle              VehicleEntity object to be assigned
     * @param primaryDriverLicense String object - driving license number of primary driver
     * @param coDriverLicense      String object - driving license number of co-driver
     */
    void assignToDrivers(VehicleEntity vehicle, String primaryDriverLicense, String coDriverLicense);

    /**
     * Finds vehicle with authenticated driver.
     *
     * @return Vehicle object
     */
    Vehicle getVehicleOfAuthenticatedDriver();

    /**
     * Marks vehicle as broken.
     *
     * @param id Long object - id of broken vehicle
     */
    void setBroken(Long id);

    /**
     * Turns vehicle status to the one before breaking.
     * Used when vehicle has been repaired.
     *
     * @param id Long object - id of repaired vehicle
     */
    void repaired(Long id);

    /**
     * Completes delivery for all loads assigned to vehicle.
     *
     * @param id Long object - id of vehicle
     */
    void allLoadsDelivered(Long id);

    /**
     * Rejects delivery for all loads assigned to vehicle.
     *
     * @param id Long object - id of vehicle
     */
    void rejectDelivery(Long id);
}
