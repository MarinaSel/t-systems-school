package com.training.services.impl;

import com.training.entities.DriverEntity;
import com.training.repositories.DriverRepository;
import com.training.services.interfaces.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DriverServiceImpl implements DriverService{

    @Autowired
    private DriverRepository driverRepository;

    @Override
    @Transactional
    public DriverEntity get(Long id){
        return driverRepository.getOne(id);
    }

    @Override
    @Transactional
    public DriverEntity update(DriverEntity driverEntity){
        return driverRepository.saveAndFlush(driverEntity);
    }

    @Override
    @Transactional
    public DriverEntity create(DriverEntity driverEntity){
        return driverRepository.saveAndFlush(driverEntity);
    }

    @Override
    @Transactional
    public void remove(Long id){
        driverRepository.deleteById(id);
    }

    @Override
    @Transactional
    public List<DriverEntity> getAll(){
        return driverRepository.findAll();
    }
}
