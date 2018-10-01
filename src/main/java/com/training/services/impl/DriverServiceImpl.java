package com.training.services.impl;

import com.training.model.entities.DriverEntity;
import com.training.repositories.DriverRepository;
import com.training.services.interfaces.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ComponentScan
public class DriverServiceImpl implements DriverService {

    @Autowired
    private DriverRepository driverRepository;

    @Override
    public DriverEntity get(Long id){
        return driverRepository.getOne(id);
    }

    @Override
    public DriverEntity update(DriverEntity driverEntity){
        return driverRepository.saveAndFlush(driverEntity);
    }

    @Override
    public DriverEntity create(DriverEntity driverEntity){
        return driverRepository.saveAndFlush(driverEntity);
    }

    @Override
    public void delete(DriverEntity driverEntity){
        driverRepository.delete(driverEntity);
    }

    @Override
    public List<DriverEntity> getDrivers(){
        return driverRepository.findAll();
    }
}
