package com.training.mappers;

import com.training.entities.DriverEntity;
import com.training.models.Driver;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public final class DriverMapper {

    private DriverMapper(){};

    public static DriverEntity getEntityFromModel(Driver driver){

        if(driver == null){
            return null;
        }
        DriverEntity driverEntity = new DriverEntity();

        driverEntity.setId(driver.getId());
        driverEntity.setFirstName(driver.getFirstName());
        driverEntity.setLastName(driver.getLastName());
        driverEntity.setDrivingLicenseNum(driver.getDrivingLicenseNum());
        driverEntity.setLicenseEndDate(driver.getLicenseEndDate());
        driverEntity.setStatus(driver.getStatus());
        driverEntity.setCreationDate(driver.getCreationDate());
        driverEntity.setVehicle(VehicleMapper.getEntityFromModel(driver.getVehicle()));

        return driverEntity;
    }

    public static Driver getModelFromEntity(DriverEntity driverEntity){
        if(driverEntity == null){
            return null;
        }
        Driver driver = commonFields(driverEntity);
        driver.setVehicle(VehicleMapper.getModelFromEntity(driverEntity.getVehicle()));

        return driver;
    }

    public static Driver getSimpleModelFromEntity(DriverEntity driverEntity){
        return commonFields(driverEntity);
    }

    private static Driver commonFields(DriverEntity driverEntity){

        Driver driver = new Driver();

        driver.setId(driverEntity.getId());
        driver.setFirstName(driverEntity.getFirstName());
        driver.setLastName(driverEntity.getLastName());
        driver.setDrivingLicenseNum(driverEntity.getDrivingLicenseNum());
        driver.setLicenseEndDate(driverEntity.getLicenseEndDate());
        driver.setStatus(driverEntity.getStatus());
        driver.setCreationDate(driverEntity.getCreationDate());

        return driver;
    }

    public static List<Driver> getModelListFromEntityList(List<DriverEntity> driverEntities){
        if(driverEntities == null){
            return null;
        }
        List<Driver> drivers = new LinkedList<>();

        for (DriverEntity driverEntity: driverEntities) {
            drivers.add(getModelFromEntity(driverEntity));
        }
        return drivers;
    }

    public static Set<Driver> getModelSetFromEntitySet(Set<DriverEntity> driverEntities){

        if(driverEntities == null){
            return null;
        }
        Set<Driver> drivers = new HashSet<>();

        for (DriverEntity driverEntity :
                driverEntities) {
            drivers.add(getSimpleModelFromEntity(driverEntity));
        }

        return drivers;
    }

    public static Set<DriverEntity> getEntitySetFromModelSet(Set<Driver> drivers){

        if(drivers == null){
            return null;
        }
        Set<DriverEntity> driverEntities = new HashSet<>();

        for (Driver driver : drivers) {
            driverEntities.add(getEntityFromModel(driver));
        }

        return driverEntities;
    }
}
