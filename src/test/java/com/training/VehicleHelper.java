package com.training;

import com.training.entities.VehicleEntity;

import java.util.Date;

import static com.training.entities.enums.VehicleStatus.FREE;

public final class VehicleHelper {

    private VehicleHelper() {
    }

    public static final String VEHICLE_REGISTRATION_NUMBER = "77hhhhhh";
    public static final String ANOTHER_VEHICLE_REGISTRATION_NUMBER = "11fffff";
    private static final Long VEHICLE_ID = 1L;
    private static final Long ANOTHER_VEHICLE_ID = 2L;
    private static final String MODEL = "Model";
    private static final Date DATE = new Date();
    private static final Integer CAPACITY = 10000;

    static VehicleEntity getVehicle() {
        return new VehicleEntity(VEHICLE_ID, MODEL, DATE, VEHICLE_REGISTRATION_NUMBER, CAPACITY, FREE);
    }

    static VehicleEntity getAnotherVehicle() {
        return new VehicleEntity(ANOTHER_VEHICLE_ID, MODEL, DATE, ANOTHER_VEHICLE_REGISTRATION_NUMBER, CAPACITY, FREE);
    }
}
