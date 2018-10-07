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

    @Transactional
    public LoadEntity get(Long id){
        return loadRepository.getOne(id);
    }

    @Transactional
    public void create(LoadEntity loadEntity){
        loadRepository.saveAndFlush(loadEntity);
    }

    @Transactional
    public LoadEntity update(LoadEntity loadEntity){
        return loadRepository.saveAndFlush(loadEntity);
    }

    @Transactional
    public void remove(Long id){
        loadRepository.deleteById(id);
    }

    @Transactional
    public List<LoadEntity> getAll(){
        return loadRepository.findAll();
    }

}
