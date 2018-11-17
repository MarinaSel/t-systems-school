package com.training;

import com.training.entities.LoadEntity;
import com.training.models.Load;

import java.util.Date;
import java.util.List;
import java.util.Set;

import static com.training.entities.enums.LoadStatus.ASSIGNED;
import static com.training.entities.enums.LoadStatus.NOT_ASSIGNED;
import static com.training.mappers.LoadMapper.mapEntityToModel;
import static java.util.Collections.singleton;
import static java.util.Collections.singletonList;

public final class LoadHelper {

    private LoadHelper() {
    }

    public static final Long LOAD_ID = 1L;
    private static final String TITLE = "Title";
    private static final String DESCRIPTION = "Description";
    private static final Date DATE = new Date();
    private static final Integer WEIGHT = 100;

    public static Load getLoadModelForCreation() {
        return mapEntityToModel(getLoadForCreation());
    }

    public static Load getLoadModel() {
        return mapEntityToModel(getLoad());
    }

    public static LoadEntity getLoadAssignedToVehicle() {
        LoadEntity loadEntity = getLoad();
        loadEntity.setStatus(ASSIGNED);
        loadEntity.setVehicle(VehicleHelper.getVehicle());
        return loadEntity;
    }

    public static LoadEntity getLoadAssignedToAnotherVehicle() {
        LoadEntity loadEntity = getLoad();
        loadEntity.setStatus(ASSIGNED);
        loadEntity.setVehicle(VehicleHelper.getAnotherVehicle());
        return loadEntity;
    }

    public static LoadEntity getLoadForCreationAssignedToVehicle() {
        LoadEntity loadEntity = getLoadForCreation();
        loadEntity.setStatus(ASSIGNED);
        loadEntity.setVehicle(VehicleHelper.getVehicle());
        return loadEntity;
    }

    public static LoadEntity getLoad() {
        LoadEntity loadEntity = getLoadForCreation();
        loadEntity.setId(LOAD_ID);
        return loadEntity;
    }

    public static LoadEntity getLoadForCreation() {
        return new LoadEntity(null, TITLE, DESCRIPTION, DATE, NOT_ASSIGNED, WEIGHT, null);
    }

    public static LoadEntity getLoadForCreationWithNullProperty() {
        LoadEntity loadEntity = getLoadForCreation();
        loadEntity.setTitle(null);
        return loadEntity;
    }

    public static LoadEntity getLoadForUpdateWithNullProperty() {
        LoadEntity loadEntity = getLoad();
        loadEntity.setTitle(null);
        return loadEntity;
    }

    public static List<LoadEntity> getLoadList() {
        return singletonList(getLoad());
    }

    static Set<LoadEntity> getLoadSet() {
        return singleton(getLoad());
    }
}
