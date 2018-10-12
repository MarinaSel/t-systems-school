package com.training.services.impl;

import com.training.entities.DriverEntity;
import com.training.entities.enums.DriverStatus;
import com.training.mappers.DriverMapper;
import com.training.models.Driver;
import com.training.repositories.DriverRepository;
import com.training.services.interfaces.DriverService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DriverServiceImpl implements DriverService{

    @Autowired
    private DriverRepository driverRepository;

    @Transactional
    public Driver get(Long id){
        return DriverMapper.getModelFromEntity(driverRepository.getOne(id));
    }

    @Transactional
    public Driver update(Driver driver){
        DriverEntity driverEntity = driverRepository.saveAndFlush(DriverMapper.getEntityFromModel(driver));
        return DriverMapper.getModelFromEntity(driverEntity);
    }

    @Transactional
    public void create(Driver driver){
        driverRepository.saveAndFlush(DriverMapper.getEntityFromModel(driver));
    }

    @Transactional
    public void remove(Long id){
        driverRepository.deleteById(id);
    }

    @Transactional
    public List<Driver> getAll(){
        return DriverMapper.getModelListFromEntityList(driverRepository.findAll());
    }
    @Override
    public List<Driver> getAllFree() {
        return DriverMapper.getModelListFromEntityList(driverRepository.findAllByStatus(DriverStatus.FREE));
    }
}
