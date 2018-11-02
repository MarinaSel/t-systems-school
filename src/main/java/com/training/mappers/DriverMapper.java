package com.training.mappers;

import com.training.entities.DriverEntity;
import com.training.models.Driver;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Utility class that describes static methods for mapping DriverEntity objects from Driver objects
 * and Driver objects from DriverEntity objects.
 *
 * @see DriverEntity
 * @see Driver
 */
public final class DriverMapper {

    private DriverMapper() {
    }

    /**
     * Method maps driver to driverEntity.
     *
     * @param driver Driver object to be mapped
     * @return driverEntity
     */
    public static DriverEntity mapModelToEntity(Driver driver) {

        if (driver == null) {
            return null;
        }
        DriverEntity driverEntity = new DriverEntity();

        driverEntity.setId(driver.getId());
        driverEntity.setDrivingLicenseNum(driver.getDrivingLicenseNum());
        driverEntity.setLicenseEndDate(driver.getLicenseEndDate());
        driverEntity.setStatus(driver.getStatus());
        driverEntity.setCreationDate(driver.getCreationDate());
        driverEntity.setUser(UserMapper.mapModelToEntity(driver.getUser()));

        return driverEntity;
    }

    /**
     * Method maps driverEntity to driver.
     *
     * @param driverEntity DriverEntity to be mapped
     * @return driver
     */
    public static Driver mapEntityToModel(DriverEntity driverEntity) {
        if (driverEntity == null) {
            return null;
        }
        Driver driver = commonFields(driverEntity);

        return driver;
    }

    /**
     * Method maps driverEntity to simple driver model without vehicle.
     *
     * @param driverEntity DriverEntity to be mapped
     * @return driver
     */
    private static Driver mapEntityToSimpleModel(DriverEntity driverEntity) {
        return commonFields(driverEntity);
    }

    /**
     * Method maps common fields for simple driver model and driver model with vehicle.
     *
     * @param driverEntity DriverEntity to be mapped
     * @return driver
     */
    private static Driver commonFields(DriverEntity driverEntity) {

        Driver driver = new Driver();

        driver.setId(driverEntity.getId());
        driver.setDrivingLicenseNum(driverEntity.getDrivingLicenseNum());
        driver.setLicenseEndDate(driverEntity.getLicenseEndDate());
        driver.setStatus(driverEntity.getStatus());
        driver.setCreationDate(driverEntity.getCreationDate());
        driver.setUser(UserMapper.mapEntityToModel(driverEntity.getUser()));
        return driver;
    }

    /**
     * Method maps list of DriverEntity objects to list of Driver objects.
     *
     * @param driverEntities - list of DriverEntity objects
     * @return drivers - list of Drivers object
     */
    public static List<Driver> mapEntityListToModelList(List<DriverEntity> driverEntities) {
        if (driverEntities == null) {
            return null;
        }
        List<Driver> drivers = new LinkedList<>();

        for (DriverEntity driverEntity : driverEntities) {
            drivers.add(mapEntityToModel(driverEntity));
        }
        return drivers;
    }

    /**
     * Methods maps set of DriverEntity objects to set of Driver objects
     *
     * @param driverEntities - set of DriverEntity objects
     * @return drivers - set of Drivers objects
     */
    public static Set<Driver> mapEntitySetToModelSet(Set<DriverEntity> driverEntities) {

        if (driverEntities == null) {
            return null;
        }
        Set<Driver> drivers = new HashSet<>();

        for (DriverEntity driverEntity :
                driverEntities) {
            drivers.add(mapEntityToSimpleModel(driverEntity));
        }

        return drivers;
    }

}
