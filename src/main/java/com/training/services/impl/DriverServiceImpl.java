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

import static com.training.mappers.DriverMapper.getModelFromEntity;
import static com.training.mappers.DriverMapper.getEntityFromModel;
import static com.training.mappers.DriverMapper.getModelListFromEntityList;


@Service
@Transactional
public class DriverServiceImpl implements DriverService{

    private final static Logger logger = LogManager.getLogger(DriverServiceImpl.class);

    @Autowired
    private DriverRepository driverRepository;

    @Override
    public Driver get(Long id){
        Driver driver = getModelFromEntity(driverRepository.getOne(id));
        logger.info("Got driver with id = {}", driver.getId());
        return driver;
    }

    @Override
    public Driver save(Driver driver){
        DriverEntity driverEntity = getEntityFromModel(driver);
        driverRepository.saveAndFlush(driverEntity);

        if(driver.getId() == null){
            logger.info("Created driver with id = {}", driverEntity.getId());
        }
        else{
            logger.info("Updated driver with id = {}", driver.getId());
        }
        return getModelFromEntity(driverEntity);
    }

    @Override
    public void remove(Long id){
        driverRepository.deleteById(id);
        logger.info("Deleted driver with id = {}", id);
    }

    @Override
    public List<Driver> getAll(){
        List<Driver> drivers = getModelListFromEntityList(driverRepository.findAll());
        logger.info("Found all drivers");
        return drivers;
    }

    @Override
    public List<Driver> getAllFree() {
        List<Driver> drivers = getModelListFromEntityList(driverRepository.findAllByStatus(DriverStatus.FREE));
        logger.info("Found all drivers with status FREE");
        return drivers;
    }

    @Override
    public Driver findByDrivingLicenseNum(String drivingLicenseNum) {
        Driver driver = getModelFromEntity(driverRepository.findByDrivingLicenseNum(drivingLicenseNum));
        logger.info("Found driver with driving license number = {}", drivingLicenseNum);
        return driver;
    }
}
