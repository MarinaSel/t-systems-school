package com.training.services.impl;

import com.training.model.entities.LoadEntity;
import com.training.repositories.LoadRepository;
import com.training.services.LoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoadServiceImpl implements LoadService {

    @Autowired
    LoadRepository loadRepository;

    @Override
    public LoadEntity get(Long id){
        return loadRepository.getOne(id);
    }

    @Override
    public LoadEntity create(LoadEntity loadEntity){
        return loadRepository.saveAndFlush(loadEntity);
    }

    @Override
    public LoadEntity update(LoadEntity loadEntity){
        return loadRepository.saveAndFlush(loadEntity);
    }

    @Override
    public void delete(LoadEntity loadEntity){
        loadRepository.delete(loadEntity);
    }

}
