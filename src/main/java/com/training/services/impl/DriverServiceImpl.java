package com.training.services.impl;

import com.training.entities.DriverEntity;
import com.training.mappers.DriverMapper;
import com.training.mappers.UserMapper;
import com.training.models.Driver;
import com.training.models.User;
import com.training.repositories.DriverRepository;
import com.training.services.DriverService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.training.entities.enums.DriverStatus.FREE;

@Service
@Transactional
public class DriverServiceImpl implements DriverService {

    private final static Logger LOGGER = LogManager.getLogger(DriverServiceImpl.class);

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private UserService userService;

    @Override
    public Driver get(Long id) {
        Driver driver = DriverMapper.mapEntityToModel(driverRepository.getOne(id));
        LOGGER.info("Got driver with id = {}", driver.getId());
        return driver;
    }

    @Override
    public void save(Driver driver) {
        if (driver.getId() == null) {
            User user = userService.save(driver.getUser());
            driver.setUser(user);
        }
        DriverEntity driverEntity = DriverMapper.mapModelToEntity(driver);
        driverRepository.saveAndFlush(driverEntity);
        if (driver.getId() == null) {
            LOGGER.info("Created driver with id = {}", driverEntity.getId());
        } else {
            LOGGER.info("Updated driver with id = {}", driverEntity.getId());
        }
    }

    @Override
    public void remove(Long id) {
        driverRepository.deleteById(id);
        LOGGER.info("Deleted driver with id = {}", id);
    }

    @Override
    public List<Driver> getAll() {
        List<Driver> drivers = DriverMapper.mapEntityListToModelList(driverRepository.findAll());
        LOGGER.info("Found all drivers");
        return drivers;
    }

    @Override
    public List<Driver> getAllFree() {
        List<Driver> drivers = DriverMapper.mapEntityListToModelList(driverRepository.findAllByStatus(FREE));
        LOGGER.info("Found all drivers with status FREE");
        return drivers;
    }

    @Override
    public Driver findByDrivingLicenseNum(String drivingLicenseNum) {
        Driver driver = DriverMapper.mapEntityToModel(driverRepository.findByDrivingLicenseNum(drivingLicenseNum));
        LOGGER.info("Found driver with driving license number = {}", drivingLicenseNum);
        return driver;
    }

    @Override
    public Driver findByUser(User user) {
        DriverEntity driverEntity = driverRepository.findByUser(UserMapper.mapModelToEntity(user));
        LOGGER.info("Found driver by user with login = {}", user.getLogin());
        return DriverMapper.mapEntityToModel(driverEntity);
    }
}
