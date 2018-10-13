package com.training.services.impl;

import com.training.entities.DriverEntity;
import com.training.entities.enums.DriverStatus;
import com.training.models.Driver;
import com.training.repositories.DriverRepository;
import com.training.services.interfaces.DriverService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

import static com.training.mappers.DriverMapper.*;

@Service
public class DriverServiceImpl implements DriverService{

    @Autowired
    private DriverRepository driverRepository;

    @Transactional
    public Driver get(Long id){
        return getModelFromEntity(driverRepository.getOne(id));
    }

    @Transactional
    public Driver update(Driver driver){
        DriverEntity driverEntity = driverRepository.saveAndFlush(getEntityFromModel(driver));
        return getModelFromEntity(driverEntity);
    }

    @Transactional
    public void create(Driver driver){
        driverRepository.saveAndFlush(getEntityFromModel(driver));
    }

    @Transactional
    public void remove(Long id){
        driverRepository.deleteById(id);
    }

    @Transactional
    public List<Driver> getAll(){
        return getModelListFromEntityList(driverRepository.findAll());
    }
    @Transactional
    public List<Driver> getAllFree() {
        return getModelListFromEntityList(driverRepository.findAllByStatus(DriverStatus.FREE));
    }

    @Transactional
    public Set<Driver> findByDrivingLicenseNum(String drivingLicenseNum) {
        return getModelSetFromEntitySet(driverRepository.findByDrivingLicenseNum(drivingLicenseNum));
    }
}
