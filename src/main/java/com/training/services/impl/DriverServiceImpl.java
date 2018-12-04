package com.training.services.impl;

import com.training.entities.DriverEntity;
import com.training.entities.UserEntity;
import com.training.mappers.DriverMapper;
import com.training.mappers.UserMapper;
import com.training.models.Driver;
import com.training.models.User;
import com.training.repositories.DriverRepository;
import com.training.repositories.UserRepository;
import com.training.services.DriverService;
import com.training.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.training.entities.enums.DriverStatus.FREE;

@Service
public class DriverServiceImpl implements DriverService {

    private final static Logger LOGGER = LogManager.getLogger(DriverServiceImpl.class);

    private final DriverRepository driverRepository;

    private final UserService userService;

    private final UserRepository userRepository;

    @Autowired
    public DriverServiceImpl(DriverRepository driverRepository, UserService userService, UserRepository userRepository) {
        this.driverRepository = driverRepository;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public void save(Driver driver) {
        if (driver.getUser() != null) {
            String parseLogin = driver.getUser().getLogin().replaceAll(",", "");
            driver.getUser().setLogin(parseLogin);
            User user = userService.save(driver.getUser());
            driver.setUser(user);
        }

        driverRepository.saveAndFlush(DriverMapper.mapModelToEntity(driver));
        if (driver.getId() == null) {
            LOGGER.info("Created driver with id = {}", driver.getId());
        } else {
            LOGGER.info("Updated driver with id = {}", driver.getId());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Driver find(Long id) {
        Driver driver = DriverMapper.mapEntityToModel(driverRepository.getOne(id));
        LOGGER.info("Found driver with id = {}", driver.getId());
        return driver;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Driver> findAll() {
        List<Driver> drivers = DriverMapper.mapEntityListToModelList(driverRepository.findAll());
        LOGGER.info("Found all drivers");
        return drivers;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Driver> findAllFree() {
        List<Driver> drivers = DriverMapper.mapEntityListToModelList(driverRepository.findAllByStatus(FREE));
        LOGGER.info("Found all drivers with status FREE");
        return drivers;
    }

    @Override
    @Transactional(readOnly = true)
    public Driver findByDrivingLicenseNum(String drivingLicenseNum) {
        DriverEntity driver = driverRepository.findByDrivingLicenseNum(drivingLicenseNum);
        if (driver == null) {
            LOGGER.info("Driver with driving license number = {} was not found", drivingLicenseNum);
        } else {
            LOGGER.info("Found driver by driving license number = {}", drivingLicenseNum);
        }
        return DriverMapper.mapEntityToModel(driver);
    }

    @Override
    @Transactional(readOnly = true)
    public Driver findByUser(User user) {
        DriverEntity driverEntity = driverRepository.findByUser(UserMapper.mapModelToEntity(user));
        LOGGER.info("Found driver by user with login = {}", user.getLogin());
        return DriverMapper.mapEntityToModel(driverEntity);
    }

    @Override
    @Transactional
    public void fireDriver(Long id) {
        driverRepository.setFired(id);
        LOGGER.info("Fired driver with id = {}", id);
    }

    @Override
    @Transactional(readOnly = true)
    public Driver getAuthenticatedDriver() {
        String login = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity user = userRepository.findByLogin(login);
        DriverEntity driver = driverRepository.findByUser(user);
        return DriverMapper.mapEntityToModel(driver);
    }

    @Override
    @Transactional
    public void setRest(Long id) {
        driverRepository.setRest(id);
        LOGGER.info("Driver with id = {} is resting now", id);
    }

    @Override
    @Transactional
    public void setFree(Long id) {
        driverRepository.setFree(id);
        LOGGER.info("Driver with id = {} is free now", id);
    }
}
