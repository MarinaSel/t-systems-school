package com.training;

import com.training.entities.VehicleEntity;
import com.training.models.Vehicle;

import java.util.Date;
import java.util.List;

import static com.training.DriverHelper.getCoDriver;
import static com.training.DriverHelper.getDriverWithUser;
import static com.training.DriverHelper.getPrimaryDriver;
import static com.training.entities.enums.VehicleStatus.FREE;
import static com.training.mappers.VehicleMapper.mapEntityToModel;
import static java.util.Collections.singletonList;

public final class VehicleHelper {

    public static final String VEHICLE_REGISTRATION_NUMBER = "77hhhhhh";
    public static final String ANOTHER_VEHICLE_REGISTRATION_NUMBER = "11fffff";
    public static final Long VEHICLE_ID = 1L;
    public static final Integer CAPACITY = 10000;
    private static final Long ANOTHER_VEHICLE_ID = 2L;
    private static final String MODEL = "Model";
    private static final Date DATE = new Date();

    private VehicleHelper() {
    }

    public static VehicleEntity getVehicle() {
        VehicleEntity vehicleEntity = getVehicleForCreation();
        vehicleEntity.setId(VEHICLE_ID);
        return vehicleEntity;
    }

    public static List<VehicleEntity> getVehicleList() {
        return singletonList(getVehicle());
    }

    public static Vehicle getVehicleModel() {
        return mapEntityToModel(getVehicle());
    }

    public static VehicleEntity getVehicleForCreation() {
        return new VehicleEntity(null, MODEL, DATE, VEHICLE_REGISTRATION_NUMBER, CAPACITY, FREE);
    }

    public static Vehicle getVehicleModelForCreation() {
        return mapEntityToModel(getVehicleForCreation());
    }

    public static VehicleEntity getVehicleForCreationWithNullProperty() {
        VehicleEntity vehicleEntity = getVehicleForCreation();
        vehicleEntity.setRegistrationNumber(null);
        return vehicleEntity;
    }

    public static VehicleEntity getVehicleForUpdateWithNullProperty() {
        VehicleEntity vehicleEntity = getVehicle();
        vehicleEntity.setRegistrationNumber(null);
        return vehicleEntity;
    }

    public static VehicleEntity getVehicleWithDriverWithUser() {
        VehicleEntity vehicleEntity = getVehicle();
        vehicleEntity.setPrimaryDriver(getDriverWithUser());
        return vehicleEntity;
    }

    public static VehicleEntity getVehicleWithLoadsAndDrivers() {
        VehicleEntity vehicleEntity = getVehicleWithDrivers();
        vehicleEntity.setLoads(LoadHelper.getLoadSet());
        return vehicleEntity;
    }

    public static VehicleEntity getVehicleWithDrivers() {
        VehicleEntity vehicleEntity = getVehicle();
        vehicleEntity.setPrimaryDriver(getPrimaryDriver());
        vehicleEntity.setCoDriver(getCoDriver());
        return vehicleEntity;
    }

    static VehicleEntity getAnotherVehicle() {
        return new VehicleEntity(ANOTHER_VEHICLE_ID, MODEL, DATE, ANOTHER_VEHICLE_REGISTRATION_NUMBER, CAPACITY, FREE);
    }
}
