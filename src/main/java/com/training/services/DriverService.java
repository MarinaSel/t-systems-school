package com.training.services;

import com.training.models.Driver;

import java.util.List;

/**
 * Interface that extends BaseService and defines methods for operations with drivers.
 *
 * @see BaseService
 * @see Driver
 */
public interface DriverService extends BaseService<Driver, Long> {

    /**
     * Finds all drivers.
     *
     * @return List of all found Driver objects
     */
    List<Driver> getAll();

    /**
     * Finds all drivers with status 'FREE'.
     *
     * @return List of found Driver objects with status 'FREE'
     */
    List<Driver> getAllFree();

    /**
     * Finds driver by driving license number.
     *
     * @param drivingLicenseNum String object with driving license number for search
     * @return Driver object with specified id
     */
    Driver findByDrivingLicenseNum(String drivingLicenseNum);

}
