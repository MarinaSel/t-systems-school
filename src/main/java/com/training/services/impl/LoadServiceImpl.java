package com.training.services.impl;

import com.training.entities.LoadEntity;
import com.training.models.Load;
import com.training.repositories.LoadRepository;
import com.training.services.interfaces.LoadService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.training.mappers.LoadMapper.getModelFromEntity;
import static com.training.mappers.LoadMapper.getEntityFromModel;
import static com.training.mappers.LoadMapper.getModelListFromEntityList;

@Service
@Transactional
public class LoadServiceImpl implements LoadService {

    private final static Logger logger = LogManager.getLogger(LoadServiceImpl.class);

    @Autowired
    private LoadRepository loadRepository;

    @Override
    public Load get(Long id){
        Load load = getModelFromEntity(loadRepository.getOne(id));
        logger.info("Got load with id = {}", load.getId());
        return load;
    }

    @Override
    public Load save(Load load){
        LoadEntity loadEntity = getEntityFromModel(load);
        loadRepository.saveAndFlush(loadEntity);
        if(load.getId() == null){
            logger.info("Created load with id = {}", loadEntity.getId());
        }
        else{
            logger.info("Updated load with id = {}", load.getId());
        }
        return getModelFromEntity(loadEntity);
    }

    @Override
    public void remove(Long id){
        loadRepository.deleteById(id);
        logger.info("Deleted load with id = {}", id);
    }

    @Override
    public List<Load> getAll(){
        List<Load> loads = getModelListFromEntityList(loadRepository.findAll());
        logger.info("Found all loads");
        return loads;
    }

}
