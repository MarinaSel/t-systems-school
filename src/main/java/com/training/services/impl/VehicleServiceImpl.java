package com.training.services.impl;

import com.training.entities.DriverEntity;
import com.training.entities.VehicleEntity;
import com.training.entities.enums.VehicleStatus;
import com.training.models.Load;
import com.training.models.Vehicle;
import com.training.repositories.DriverRepository;
import com.training.repositories.LoadRepository;
import com.training.repositories.VehicleRepository;
import com.training.services.VehicleService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Iterator;
import java.util.List;

import static com.training.mappers.VehicleMapper.mapEntityListToModelList;
import static com.training.mappers.VehicleMapper.mapEntityToModel;
import static com.training.mappers.VehicleMapper.mapModelToEntity;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final static Logger LOGGER = LogManager.getLogger(VehicleServiceImpl.class);

    private final VehicleRepository vehicleRepository;

    private final DriverRepository driverRepository;

    private final LoadRepository loadRepository;

    @Autowired
    public VehicleServiceImpl(VehicleRepository vehicleRepository, DriverRepository driverRepository, LoadRepository loadRepository) {
        this.vehicleRepository = vehicleRepository;
        this.driverRepository = driverRepository;
        this.loadRepository = loadRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Vehicle find(Long id) {
        Vehicle vehicle = mapEntityToModel(vehicleRepository.getOne(id));
        LOGGER.info("Found vehicle with id = {}", vehicle.getId());
        return vehicle;
    }

    @Override
    @Transactional
    public void save(Vehicle vehicle) {
        VehicleEntity vehicleEntity = mapModelToEntity(vehicle);
        vehicleRepository.saveAndFlush(vehicleEntity);

        if (vehicle.getId() == null) {
            LOGGER.info("Created vehicle with id = {}", vehicleEntity.getId());
        } else {
            LOGGER.info("Updated vehicle with id = {}", vehicleEntity.getId());
        }
        mapEntityToModel(vehicleEntity);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        vehicleRepository.deleteById(id);
        LOGGER.info("Deleted vehicle with id = {}", id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Vehicle> findAll() {
        List<Vehicle> vehicles = mapEntityListToModelList(vehicleRepository.findAll());
        LOGGER.info("Found all vehicles");
        return vehicles;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Vehicle> findAllFreeWithNecessaryCapacity(Integer necessaryCapacity) {
        List<Vehicle> vehicles = mapEntityListToModelList(vehicleRepository.findAllByStatus(VehicleStatus.FREE));
        Iterator<Vehicle> iterator = vehicles.iterator();

        while (iterator.hasNext()) {
            Vehicle vehicle = iterator.next();
            int sumWeight = 0;
            for (Load load : vehicle.getLoads()) {
                sumWeight += load.getWeight();
            }
            if ((vehicle.getCapacity() - sumWeight < necessaryCapacity)) {
                iterator.remove();
            }
        }
        LOGGER.info("Found all vehicles with capacity = {} and status FREE", necessaryCapacity);
        return vehicles;
    }

    @Override
    @Transactional(readOnly = true)
    public Vehicle findByRegistrationNumber(String registrationNumber) {
        Vehicle vehicle = mapEntityToModel(vehicleRepository.findVehicleEntityByRegistrationNumber(registrationNumber));
        LOGGER.info("Found vehicle by registration number = {}", registrationNumber);
        return vehicle;
    }

    @Override
    @Transactional
    public void checkIfCompletedDelivery(VehicleEntity vehicleEntity) {
        if (CollectionUtils.isEmpty(vehicleEntity.getLoads())) {
            Long primaryDriverId = (vehicleEntity.getPrimaryDriver() == null) ? null
                    : vehicleEntity.getPrimaryDriver().getId();
            Long coDriverId = (vehicleEntity.getCoDriver() == null) ? null : vehicleEntity.getCoDriver().getId();

            driverRepository.setFree(primaryDriverId, coDriverId);
            vehicleRepository.setFree(vehicleEntity.getId());
            LOGGER.info("Vehicle with id {} completed delivery", vehicleEntity.getId());
        }
    }

    @Override
    @Transactional
    public void startDelivery(Long id) {
        VehicleEntity vehicleEntity = vehicleRepository.getOne(id);
        if (CollectionUtils.isNotEmpty(vehicleEntity.getLoads())) {
            Long primaryDriverId = (vehicleEntity.getPrimaryDriver() == null)
                    ? null : vehicleEntity.getPrimaryDriver().getId();
            Long coDriverId = (vehicleEntity.getCoDriver() == null) ? null : vehicleEntity.getCoDriver().getId();

            driverRepository.setWorking(primaryDriverId, coDriverId);
            vehicleRepository.setWorking(id);
            loadRepository.setInProgress(vehicleEntity);
            LOGGER.info("Vehicle with id {} started delivery", id);
        }
    }

    @Override
    @Transactional
    public void assignToDrivers(Vehicle vehicle, String primaryDriverLicense, String coDriverLicense) {
        VehicleEntity vehicleEntity = mapModelToEntity(vehicle);
        if (!StringUtils.isEmpty(primaryDriverLicense)) {
            DriverEntity driverEntity = driverRepository.findByDrivingLicenseNum(primaryDriverLicense);
            vehicleEntity.setPrimaryDriver(driverEntity);
            assignDriver(driverEntity.getId());
        }
        if (!StringUtils.isEmpty(coDriverLicense)) {
            DriverEntity driverEntity = driverRepository.findByDrivingLicenseNum(coDriverLicense);
            vehicleEntity.setCoDriver(driverEntity);
            assignDriver(driverEntity.getId());
        }
        vehicleRepository.saveAndFlush(vehicleEntity);
    }

    @Transactional
    void assignDriver(Long driverId) {
        driverRepository.setAssigned(driverId);
        LOGGER.info("Driver with id {} was assigned to vehicle", driverId);
    }
}
