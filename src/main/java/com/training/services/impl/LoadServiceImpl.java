package com.training.services.impl;

import com.training.entities.LoadEntity;
import com.training.mappers.LoadMapper;
import com.training.models.Load;
import com.training.repositories.LoadRepository;
import com.training.services.interfaces.LoadService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LoadServiceImpl implements LoadService {

    @Autowired
    LoadRepository loadRepository;

    @Transactional
    public Load get(Long id){
        return LoadMapper.getModelFromEntity(loadRepository.getOne(id));
    }

    @Transactional
    public void create(Load load){
        loadRepository.saveAndFlush(LoadMapper.getEntityFromModel(load));
    }

    @Transactional
    public Load update(Load load){
        LoadEntity loadEntity = LoadMapper.getEntityFromModel(load);
        return LoadMapper.getModelFromEntity(loadEntity);
    }

    @Transactional
    public void remove(Long id){
        loadRepository.deleteById(id);
    }

    @Transactional
    public List<Load> getAll(){
        return LoadMapper.getModelListFromEntityList(loadRepository.findAll());
    }

}
