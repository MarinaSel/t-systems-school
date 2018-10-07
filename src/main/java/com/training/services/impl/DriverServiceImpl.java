package com.training.services.impl;

import com.training.entities.DriverEntity;
import com.training.repositories.DriverRepository;
import com.training.services.interfaces.DriverService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DriverServiceImpl implements DriverService{

    @Autowired
    private DriverRepository driverRepository;

    @Transactional
    public DriverEntity get(Long id){
        return driverRepository.getOne(id);
    }

    @Transactional
    public DriverEntity update(DriverEntity driverEntity){
        return driverRepository.saveAndFlush(driverEntity);
    }

    @Transactional
    public void create(DriverEntity driverEntity){
        driverRepository.saveAndFlush(driverEntity);
    }

    @Transactional
    public void remove(Long id){
        driverRepository.deleteById(id);
    }

    @Transactional
    public List<DriverEntity> getAll(){
        return driverRepository.findAll();
    }
}
