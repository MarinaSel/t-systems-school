package com.training.mappers;

import com.training.entities.DriverEntity;
import com.training.models.Driver;

import java.util.LinkedList;
import java.util.List;

/**
 * Utility class that provides static methods for mapping DriverEntity objects to Driver objects
 * and Driver objects to DriverEntity objects.
 *
 * @see DriverEntity
 * @see Driver
 */
public final class DriverMapper {

    private DriverMapper() {
    }

    /**
     * Maps Driver object to DriverEntity object.
     *
     * @param driver Driver object to be mapped
     * @return DriverEntity object - result of mapping
     */
    public static DriverEntity mapModelToEntity(Driver driver) {
        if (driver == null) {
            return null;
        }
        DriverEntity driverEntity = new DriverEntity();
        driverEntity.setId(driver.getId());
        driverEntity.setDrivingLicenseNum(driver.getDrivingLicenseNum());
        driverEntity.setLicenseEndDate(driver.getLicenseEndDate());
        driverEntity.setDateOfFire(driver.getDateOfFire());
        driverEntity.setStatus(driver.getStatus());
        driverEntity.setCreationDate(driver.getCreationDate());
        driverEntity.setUser(UserMapper.mapModelToEntity(driver.getUser()));
        return driverEntity;
    }

    /**
     * Maps DriverEntity object to Driver object.
     *
     * @param driverEntity DriverEntity object to be mapped
     * @return Driver object - result of mapping
     */
    public static Driver mapEntityToModel(DriverEntity driverEntity) {
        if (driverEntity == null) {
            return null;
        }
        Driver driver = new Driver();
        driver.setId(driverEntity.getId());
        driver.setDrivingLicenseNum(driverEntity.getDrivingLicenseNum());
        driver.setLicenseEndDate(driverEntity.getLicenseEndDate());
        driver.setDateOfFire(driverEntity.getDateOfFire());
        driver.setStatus(driverEntity.getStatus());
        driver.setCreationDate(driverEntity.getCreationDate());
        driver.setUser(UserMapper.mapEntityToModel(driverEntity.getUser()));
        return driver;
    }

    /**
     * Maps List of DriverEntity objects to List of Driver objects.
     *
     * @param driverEntities List of DriverEntity objects to be mapped
     * @return List of Driver objects - result of mapping
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
}
