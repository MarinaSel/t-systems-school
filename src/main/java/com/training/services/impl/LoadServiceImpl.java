package com.training.services.impl;

import com.training.entities.LoadEntity;
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

    @Override
    @Transactional
    public LoadEntity get(Long id){
        return loadRepository.getOne(id);
    }

    @Override
    @Transactional
    public LoadEntity create(LoadEntity loadEntity){
        return loadRepository.saveAndFlush(loadEntity);
    }

    @Override
    @Transactional
    public LoadEntity update(LoadEntity loadEntity){
        return loadRepository.saveAndFlush(loadEntity);
    }

    @Override
    @Transactional
    public void remove(Long id){
        loadRepository.deleteById(id);
    }

    @Override
    @Transactional
    public List<LoadEntity> getAll(){
        return loadRepository.findAll();
    }

}
