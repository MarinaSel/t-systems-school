package com.training.mappers;

import com.training.entities.LoadEntity;
import com.training.models.Load;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Utility class that describes static methods for mapping LoadEntity objects from Load objects
 * and Load objects from LoadEntity objects.
 *
 * @see LoadEntity
 * @see Load
 */
public final class LoadMapper {

    private LoadMapper() {
    }

    /**
     * Method maps Load object to LoadEntity object.
     *
     * @param load Load object to be mapped
     * @return loadEntity
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

        return loadEntity;
    }

    /**
     * Method maps LoadEntity object to Load object.
     *
     * @param loadEntity LoadEntity to be mapped
     * @return load
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
     * Method maps LoadEntity object to simple Load model without set of loads.
     *
     * @param loadEntity LoadEntity to be mapped
     * @return load
     */
    private static Load mapEntityToSimpleModel(LoadEntity loadEntity) {

        if (loadEntity == null) {
            return null;
        }

        return commonFields(loadEntity);
    }

    /**
     * Method maps common fields for simple Load model and Load model with set of loads.
     *
     * @param loadEntity LoadEntity to be mapped
     * @return load
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

        return load;

    }

    /**
     * Method maps list of LoadEntity objects to list of Load objects.
     *
     * @param loadEntities list of LoadEntity objects
     * @return loads list of Load objects
     */
    public static List<Load> mapEntityListToModelList(List<LoadEntity> loadEntities) {
        List<Load> loads = new LinkedList<>();

        for (LoadEntity loadEntity :
                loadEntities) {
            loads.add(mapEntityToModel(loadEntity));
        }

        return loads;
    }

    /**
     * Methods maps set of LoadEntity objects to set of Load objects
     *
     * @param loadEntities set of LoadEntity objects
     * @return loads set of Load objects
     */
    public static Set<Load> mapEntitySetToModelSet(Set<LoadEntity> loadEntities) {
        if (loadEntities == null) {
            return null;
        }
        Set<Load> loads = new HashSet<>();

        for (LoadEntity loadEntity :
                loadEntities) {
            loads.add(mapEntityToSimpleModel(loadEntity));
        }

        return loads;
    }
}
