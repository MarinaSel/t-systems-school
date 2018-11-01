package com.training.services.impl;

import com.training.entities.DriverEntity;
import com.training.entities.enums.DriverStatus;
import com.training.models.Driver;
import com.training.repositories.DriverRepository;
import com.training.services.DriverService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.training.mappers.DriverMapper.mapEntityListToModelList;
import static com.training.mappers.DriverMapper.mapEntityToModel;
import static com.training.mappers.DriverMapper.mapModelToEntity;

@Service
@Transactional
public class DriverServiceImpl implements DriverService {

    private final static Logger logger = LogManager.getLogger(DriverServiceImpl.class);

    @Autowired
    private DriverRepository driverRepository;

    @Override
    public Driver get(Long id) {
        Driver driver = mapEntityToModel(driverRepository.getOne(id));
        logger.info("Got driver with id = {}", driver.getId());
        return driver;
    }

    @Override
    public void save(Driver driver) {
        DriverEntity driverEntity = mapModelToEntity(driver);
        driverRepository.saveAndFlush(driverEntity);

        if (driver.getId() == null) {
            logger.info("Created driver with id = {}", driverEntity.getId());
        } else {
            logger.info("Updated driver with id = {}", driver.getId());
        }
        mapEntityToModel(driverEntity);
    }

    @Override
    public void remove(Long id) {
        driverRepository.deleteById(id);
        logger.info("Deleted driver with id = {}", id);
    }

    @Override
    public List<Driver> getAll() {
        List<Driver> drivers = mapEntityListToModelList(driverRepository.findAll());
        logger.info("Found all drivers");
        return drivers;
    }

    @Override
    public List<Driver> getAllFree() {
        List<Driver> drivers = mapEntityListToModelList(driverRepository.findAllByStatus(DriverStatus.FREE));
        logger.info("Found all drivers with status FREE");
        return drivers;
    }

    @Override
    public Driver findByDrivingLicenseNum(String drivingLicenseNum) {
        Driver driver = mapEntityToModel(driverRepository.findByDrivingLicenseNum(drivingLicenseNum));
        logger.info("Found driver with driving license number = {}", drivingLicenseNum);
        return driver;
    }
}
