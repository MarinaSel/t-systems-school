package com.training.mappers;

import com.training.entities.LoadEntity;
import com.training.models.Load;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public final class LoadMapper {

    private LoadMapper(){};

    public static LoadEntity getEntityFromModel(Load load){
        LoadEntity loadEntity = new LoadEntity();

        loadEntity.setId(load.getId());
        loadEntity.setWeight(load.getWeight());
        loadEntity.setStatus(load.getStatus());
        loadEntity.setCreationDate(load.getCreationDate());
        loadEntity.setVehicle(VehicleMapper.getEntityFromModel(load.getVehicle()));

        return loadEntity;
    }

    public static Load getModelFromEntity(LoadEntity loadEntity){

        if(loadEntity == null){
            return null;
        }
        Load load = commonFields(loadEntity);

        load.setVehicle(VehicleMapper.getSimpleModelFromEntity(loadEntity.getVehicle()));

        return load;
    }

    public static Load getSimpleModelFromEntity(LoadEntity loadEntity){

        if(loadEntity == null){
            return null;
        }
        Load load = commonFields(loadEntity);

        return load;
    }

    private static Load commonFields(LoadEntity loadEntity){
        Load load = new Load();

        load.setId(loadEntity.getId());
        load.setWeight(loadEntity.getWeight());
        load.setStatus(loadEntity.getStatus());
        load.setCreationDate(loadEntity.getCreationDate());
        load.setVehicle(VehicleMapper.getSimpleModelFromEntity(loadEntity.getVehicle()));

        return load;

    }

    public static List<Load> getModelListFromEntityList(List<LoadEntity> loadEntities){
        List<Load> loads = new LinkedList<>();

        for (LoadEntity loadEntity :
                loadEntities) {
            loads.add(getModelFromEntity(loadEntity));
        }

        return loads;
    }

    public static Set<Load> getModelSetFromEntitySet(Set<LoadEntity> loadEntities){
       /* if(loadEntities == null){
            return null;
        }*/
        Set<Load> loads = new HashSet<>();

        for (LoadEntity loadEntity :
                loadEntities) {
            loads.add(getSimpleModelFromEntity(loadEntity));
        }

        return loads;
    }

    public static Set<LoadEntity> getEntitySetFromModelSet(Set<Load> loads){

        if(loads == null){
            return null;
        }
        Set<LoadEntity> loadEntities = new HashSet<>();

        for (Load load :
                loads) {
            loadEntities.add(getEntityFromModel(load));
        }

        return loadEntities;
    }
}
