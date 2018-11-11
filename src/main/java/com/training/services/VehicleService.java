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
     * Checks loads of vehicle and if there is no loads
     * then marks drivers as free and removes them from vehicle,
     * marks vehicle as free. Used when completing delivery or
     * assigning new vehicle to load.
     *
     * @param vehicleEntity VehicleEntity object to be checked
     */
    void checkIfVehicleIsEmpty(VehicleEntity vehicleEntity);

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
     * @param vehicle              Vehicle object to be assigned
     * @param primaryDriverLicense String object - driving license number of primary driver
     * @param coDriverLicense      String object - driving license number of co-driver
     */
    void assignToDrivers(Vehicle vehicle, String primaryDriverLicense, String coDriverLicense);
}
