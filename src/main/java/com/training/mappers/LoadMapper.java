package com.training.mappers;

import com.training.entities.LoadEntity;
import com.training.models.Load;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Utility class that provides static methods for mapping LoadEntity objects to Load objects
 * and Load objects to LoadEntity objects.
 *
 * @see LoadEntity
 * @see Load
 */
public final class LoadMapper {

    private LoadMapper() {
    }

    /**
     * Maps Load object to LoadEntity object.
     *
     * @param load Load object to be mapped
     * @return LoadEntity object - result of mapping
     */
    public static LoadEntity mapModelToEntity(Load load) {
        if (load == null)
            return null;
        LoadEntity loadEntity = new LoadEntity();
        loadEntity.setId(load.getId());
        loadEntity.setTitle(load.getTitle());
        loadEntity.setDescription(load.getDescription());
        loadEntity.setDayOfDelivery(load.getDayOfDelivery());
        loadEntity.setWeight(load.getWeight());
        loadEntity.setStatus(load.getStatus());
        loadEntity.setCreationDate(load.getCreationDate());
        loadEntity.setVehicle(VehicleMapper.mapModelToEntity(load.getVehicle()));
        loadEntity.setPickUpLocation(LocationMapper.mapModelToEntity(load.getPickUpLocation()));
        loadEntity.setDeliveryLocation(LocationMapper.mapModelToEntity(load.getDeliveryLocation()));
        loadEntity.setHistory(HistoryMapper.mapModelToEntity(load.getHistory()));
        return loadEntity;
    }

    /**
     * Maps LoadEntity object to Load object.
     *
     * @param loadEntity LoadEntity object to be mapped
     * @return Load object - result of mapping
     */
    public static Load mapEntityToModel(LoadEntity loadEntity) {
        if (loadEntity == null) {
            return null;
        }
        Load load = commonFields(loadEntity);
        load.setVehicle(VehicleMapper.mapEntityToSimpleModel(loadEntity.getVehicle()));
        return load;
    }

    /**
     * Maps LoadEntity object to simple Load object.
     *
     * @param loadEntity LoadEntity object to be mapped
     * @return Load object - result of mapping
     */
    private static Load mapEntityToSimpleModel(LoadEntity loadEntity) {
        if (loadEntity == null) {
            return null;
        }
        return commonFields(loadEntity);
    }

    /**
     * Maps common fields for simple Load object and Load object.
     *
     * @param loadEntity LoadEntity object to be mapped
     * @return Load object - result of mapping
     */
    private static Load commonFields(LoadEntity loadEntity) {
        Load load = new Load();
        load.setId(loadEntity.getId());
        load.setTitle(loadEntity.getTitle());
        load.setDescription(loadEntity.getDescription());
        load.setDayOfDelivery(loadEntity.getDayOfDelivery());
        load.setWeight(loadEntity.getWeight());
        load.setStatus(loadEntity.getStatus());
        load.setCreationDate(loadEntity.getCreationDate());
        load.setVehicle(VehicleMapper.mapEntityToSimpleModel(loadEntity.getVehicle()));
        load.setPickUpLocation(LocationMapper.mapEntityToModel(loadEntity.getPickUpLocation()));
        load.setDeliveryLocation(LocationMapper.mapEntityToModel(loadEntity.getDeliveryLocation()));
        load.setHistory(HistoryMapper.mapEntityToModel(loadEntity.getHistory()));
        return load;

    }

    /**
     * Maps List of LoadEntity objects to List of Load objects.
     *
     * @param loadEntities List of LoadEntity objects to be mapped
     * @return List of Load objects - result of mapping
     */
    public static List<Load> mapEntityListToModelList(List<LoadEntity> loadEntities) {
        List<Load> loads = new LinkedList<>();
        for (LoadEntity loadEntity : loadEntities) {
            loads.add(mapEntityToModel(loadEntity));
        }
        return loads;
    }

    /**
     * Maps Set of LoadEntity objects to Set of Load objects.
     *
     * @param loadEntities Set of LoadEntity objects to be mapped
     * @return Set of Load objects - result of mapping
     */
    static Set<Load> mapEntitySetToModelSet(Set<LoadEntity> loadEntities) {
        if (loadEntities == null) {
            return null;
        }
        Set<Load> loads = new HashSet<>();
        for (LoadEntity loadEntity : loadEntities) {
            loads.add(mapEntityToSimpleModel(loadEntity));
        }
        return loads;
    }
}
