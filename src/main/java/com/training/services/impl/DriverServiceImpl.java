package com.training.services.impl;

import com.training.entities.DriverEntity;
import com.training.entities.UserEntity;
import com.training.entities.enums.DriverStatus;
import com.training.mappers.DriverMapper;
import com.training.mappers.UserMapper;
import com.training.models.Driver;
import com.training.repositories.DriverRepository;
import com.training.repositories.UserRepository;
import com.training.services.DriverService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.training.mappers.DriverMapper.mapEntityListToModelList;
import static com.training.mappers.DriverMapper.mapEntityToModel;

@Service
@Transactional
public class DriverServiceImpl implements DriverService {

    private final static Logger LOGGER = LogManager.getLogger(DriverServiceImpl.class);

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Driver get(Long id) {
        Driver driver = mapEntityToModel(driverRepository.getOne(id));
        LOGGER.info("Got driver with id = {}", driver.getId());
        return driver;
    }

    @Override
    public void save(Driver driver) {
        DriverEntity driverEntity = DriverMapper.mapModelToEntity(driver);
        UserEntity userEntity = UserMapper.mapModelToEntity(driver.getUser());

        encodeAndSaveUser(userEntity);
        driverEntity.setUser(userEntity);
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
        List<Driver> drivers = mapEntityListToModelList(driverRepository.findAll());
        LOGGER.info("Found all drivers");
        return drivers;
    }

    @Override
    public List<Driver> getAllFree() {
        List<Driver> drivers = mapEntityListToModelList(driverRepository.findAllByStatus(DriverStatus.FREE));
        LOGGER.info("Found all drivers with status FREE");
        return drivers;
    }

    @Override
    public Driver findByDrivingLicenseNum(String drivingLicenseNum) {
        Driver driver = mapEntityToModel(driverRepository.findByDrivingLicenseNum(drivingLicenseNum));
        LOGGER.info("Found driver with driving license number = {}", drivingLicenseNum);
        return driver;
    }

    private void encodeAndSaveUser(UserEntity userEntity) {
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userRepository.saveAndFlush(userEntity);
    }
}
