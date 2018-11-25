package com.training;

import com.training.entities.DriverEntity;
import com.training.models.Driver;

import java.util.Date;
import java.util.List;

import static com.training.entities.enums.DriverStatus.FREE;
import static com.training.mappers.DriverMapper.mapEntityToModel;
import static java.util.Collections.singletonList;

public final class DriverHelper {

    public static final String PRIMARY_DRIVER_LICENSE = "12AA34567";
    public static final String CO_DRIVER_LICENSE = "91BB23456";
    public static final Long PRIMARY_DRIVER_ID = 1L;
    public static final Long CO_DRIVER_ID = 2L;
    private static final Date DATE = new Date();

    private DriverHelper() {
    }

    public static DriverEntity getDriverForCreation() {
        return getDriver(null, PRIMARY_DRIVER_LICENSE);
    }

    public static Driver getDriverModelForCreation() {
        return mapEntityToModel(getDriverForCreation());
    }

    public static DriverEntity getDriverForCreationWithNullProperty() {
        return getDriver(null, null);
    }

    public static DriverEntity getDriverForUpdateWithNullProperty() {
        return getDriver(PRIMARY_DRIVER_ID, null);
    }

    public static DriverEntity getPrimaryDriver() {
        return getDriver(PRIMARY_DRIVER_ID, PRIMARY_DRIVER_LICENSE);
    }

    public static DriverEntity getCoDriver() {
        return getDriver(CO_DRIVER_ID, CO_DRIVER_LICENSE);
    }

    public static DriverEntity getDriverWithUser() {
        DriverEntity driverEntity = getPrimaryDriver();
        driverEntity.setUser(UserHelper.getUser());
        return driverEntity;
    }

    public static List<DriverEntity> getDriverList() {
        return singletonList(getPrimaryDriver());
    }

    public static Driver getDriverModel() {
        return mapEntityToModel(getPrimaryDriver());
    }

    private static DriverEntity getDriver(Long id, String licenseNumber) {
        return new DriverEntity(id, licenseNumber, DATE, FREE, DATE, null);
    }
}
