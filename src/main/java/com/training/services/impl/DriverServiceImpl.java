package com.training.services.impl;

import com.training.entities.DriverEntity;
import com.training.entities.enums.DriverStatus;
import com.training.models.Driver;
import com.training.repositories.DriverRepository;
import com.training.services.interfaces.DriverService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

import static com.training.mappers.DriverMapper.*;

@Service
public class DriverServiceImpl implements DriverService{

    private final static Logger logger = LogManager.getLogger(DriverServiceImpl.class);

    @Autowired
    private DriverRepository driverRepository;

    @Transactional
    public Driver get(Long id){
        Driver driver = getModelFromEntity(driverRepository.getOne(id));
        logger.info("Got driver with id = {}", driver.getId());
        return driver;
    }

    @Transactional
    public Driver save(Driver driver){
        DriverEntity driverEntity = driverRepository.saveAndFlush(getEntityFromModel(driver));
        if(driver.getId() == null){
            logger.info("Created driver with id = {}", driverEntity.getId());
        }
        else{
            logger.info("Updated driver with id = {}", driver.getId());
        }
        return getModelFromEntity(driverEntity);
    }

    @Transactional
    public void remove(Long id){
        driverRepository.deleteById(id);
        logger.info("Deleted driver with id = {}", id);
    }

    @Transactional
    public List<Driver> getAll(){
        List<Driver> drivers = getModelListFromEntityList(driverRepository.findAll());
        logger.info("Found all drivers");
        return drivers;
    }

    @Transactional
    public List<Driver> getAllFree() {
        List<Driver> drivers = getModelListFromEntityList(driverRepository.findAllByStatus(DriverStatus.FREE));
        logger.info("Found all drivers with status FREE");
        return drivers;
    }

    @Transactional
    public Set<Driver> findByDrivingLicenseNum(String drivingLicenseNum) {
        Set<Driver> drivers = getModelSetFromEntitySet(driverRepository.findByDrivingLicenseNum(drivingLicenseNum));
        logger.info("Found drivers with driving license number = {}", drivingLicenseNum);
        return drivers;
    }
}
