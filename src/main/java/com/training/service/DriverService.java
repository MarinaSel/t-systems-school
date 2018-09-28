package com.training.service;

import com.training.model.entities.DriverEntity;
import com.training.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@ComponentScan
public class DriverService{

    private final DriverRepository driverRepository;

    @Autowired
    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    public DriverEntity get(Long id){
        return driverRepository.getOne(id);
    }

    public DriverEntity update(DriverEntity driverEntity){
        return driverRepository.saveAndFlush(driverEntity);
    }

    public DriverEntity create(DriverEntity driverEntity){
        return driverRepository.saveAndFlush(driverEntity);
    }




}
