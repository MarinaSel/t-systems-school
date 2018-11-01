package com.training.services;

import com.training.models.Driver;

import java.util.List;

/**
 * Interface for driver service which describes operations for Driver objects extends BaseService with CRUD operations.
 */
public interface DriverService extends BaseService<Driver, Long> {

    /**
     * Method for finding all drivers.
     */
    List<Driver> getAll();

    /**
     * Method for finding all drivers wiith status 'FREE'.
     */
    List<Driver> getAllFree();

    /**
     * Method for finding driver by driving license number.
     *
     * @param drivingLicenseNum driving license number for search
     */
    Driver findByDrivingLicenseNum(String drivingLicenseNum);

}
