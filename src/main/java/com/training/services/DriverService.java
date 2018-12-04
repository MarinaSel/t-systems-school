package com.training.services;

import com.training.models.Driver;
import com.training.models.User;

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
    List<Driver> findAll();

    /**
     * Finds all free drivers.
     *
     * @return List of found Driver objects with status 'FREE'
     */
    List<Driver> findAllFree();

    /**
     * Finds driver by driving license number.
     *
     * @param drivingLicenseNum String object with driving license number for search
     * @return Driver object with specified driving license number
     */
    Driver findByDrivingLicenseNum(String drivingLicenseNum);

    /**
     * Finds driver by user.
     *
     * @param user User object for search
     * @return found Driver object assigned to specified user
     */
    Driver findByUser(User user);

    /**
     * Marks driver as fired. Used when firing driver.
     *
     * @param id Long object - id of driver to be fired
     */
    void fireDriver(Long id);

    /**
     * Finds authenticated driver.
     *
     * @return found Driver object
     */
    Driver getAuthenticatedDriver();

    /**
     * Marks driver as resting.
     *
     * @param id Long object - id of resting driver
     */
    void setRest(Long id);

    /**
     * Marks driver as free.
     *
     * @param id Long object - id of driver to be set free
     */
    void setFree(Long id);
}
