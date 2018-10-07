package com.training.mappers;

import com.training.entities.LoadEntity;
import com.training.models.Load;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class LoadMapper {

    public static LoadEntity getEntityFromModel(Load load){
        LoadEntity loadEntity = new LoadEntity();

        load.setId(loadEntity.getId());
        load.setWeight(loadEntity.getWeight());
        load.setLoadStatus(loadEntity.getStatus());

        return loadEntity;
    }

    public static Load getModelFromEntity(LoadEntity loadEntity){
        Load load = new Load();

        load.setId(loadEntity.getId());
        load.setWeight(loadEntity.getWeight());
        load.setLoadStatus(loadEntity.getStatus());

        return load;
    }

    public static List<Load> getModelListFromEntityList(List<LoadEntity> loadEntities){
        List<Load> loads = new LinkedList<>();

        for (LoadEntity loadEntity :
                loadEntities) {
            loads.add(LoadMapper.getModelFromEntity(loadEntity));
        }

        return loads;
    }

    public static Set<Load> getModelSetFromEntitySet(Set<LoadEntity> loadEntities){
        Set<Load> loads = new HashSet<>();

        for (LoadEntity loadEntity :
                loadEntities) {
            loads.add(LoadMapper.getModelFromEntity(loadEntity));
        }

        return loads;
    }

    public static Set<LoadEntity> getEntitySetFromModelSet(Set<Load> loads){
        Set<LoadEntity> loadEntities = new HashSet<>();

        for (Load load :
                loads) {
            loadEntities.add(LoadMapper.getEntityFromModel(load));
        }

        return loadEntities;
    }
}
