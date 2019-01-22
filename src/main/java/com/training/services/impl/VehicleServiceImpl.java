package com.training.services.impl;

import com.training.entities.DriverEntity;
import com.training.entities.HistoryEntity;
import com.training.entities.VehicleEntity;
import com.training.entities.enums.VehicleStatus;
import com.training.mappers.DriverMapper;
import com.training.mappers.VehicleMapper;
import com.training.models.Driver;
import com.training.models.Load;
import com.training.models.Vehicle;
import com.training.repositories.DriverRepository;
import com.training.repositories.HistoryRepository;
import com.training.repositories.LoadRepository;
import com.training.repositories.VehicleRepository;
import com.training.services.DriverService;
import com.training.services.VehicleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Iterator;
import java.util.List;

import static com.training.entities.enums.LoadStatus.IN_PROGRESS;
import static com.training.entities.enums.VehicleStatus.BROKEN;
import static com.training.entities.enums.VehicleStatus.FREE;
import static com.training.mappers.VehicleMapper.mapEntityListToModelList;
import static com.training.mappers.VehicleMapper.mapEntityToModel;
import static com.training.mappers.VehicleMapper.mapModelToEntity;
import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final static Logger LOGGER = LogManager.getLogger(VehicleServiceImpl.class);

    private final VehicleRepository vehicleRepository;

    private final DriverRepository driverRepository;

    private final LoadRepository loadRepository;

    private final DriverService driverService;

    private final HistoryRepository historyRepository;

    @Autowired
    public VehicleServiceImpl(VehicleRepository vehicleRepository, DriverRepository driverRepository,
                              LoadRepository loadRepository, DriverService driverService, HistoryRepository historyRepository) {
        this.vehicleRepository = vehicleRepository;
        this.driverRepository = driverRepository;
        this.loadRepository = loadRepository;
        this.driverService = driverService;
        this.historyRepository = historyRepository;
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
            int sumWeight = vehicle.getLoads().stream().mapToInt(Load::getWeight).sum();
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
        if (vehicle == null) {
            LOGGER.info("Vehicle with registration number = {} was not found", registrationNumber);
        } else {
            LOGGER.info("Found vehicle by registration number = {}", registrationNumber);
        }
        return vehicle;
    }

    @Override
    @Transactional
    public void freeVehicleAndDrivers(VehicleEntity vehicleEntity) {
        Long primaryDriverId = (vehicleEntity.getPrimaryDriver() == null) ? null
                : vehicleEntity.getPrimaryDriver().getId();
        Long coDriverId = (vehicleEntity.getCoDriver() == null) ? null : vehicleEntity.getCoDriver().getId();

        driverRepository.setFree(primaryDriverId, coDriverId);
        if (vehicleEntity.getStatus() == BROKEN) {
            vehicleRepository.setBrokenVehicle(vehicleEntity.getId());
        } else {
            vehicleRepository.setFree(vehicleEntity.getId());
        }
    }

    @Override
    @Transactional
    public void startDelivery(Long id) {
        VehicleEntity vehicleEntity = vehicleRepository.getOne(id);
        if (isNotEmpty(vehicleEntity.getLoads())) {
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
    public void assignToDrivers(VehicleEntity vehicle, String primaryDriverLicense, String coDriverLicense) {
        if (!StringUtils.isEmpty(primaryDriverLicense)) {
            DriverEntity driverEntity = driverRepository.findByDrivingLicenseNum(primaryDriverLicense);
            vehicle.setPrimaryDriver(driverEntity);
            assignDriver(driverEntity.getId());
        }
        if (!StringUtils.isEmpty(coDriverLicense)) {
            DriverEntity driverEntity = driverRepository.findByDrivingLicenseNum(coDriverLicense);
            vehicle.setCoDriver(driverEntity);
            assignDriver(driverEntity.getId());
        }
        vehicleRepository.saveAndFlush(vehicle);
    }

    @Transactional
    void assignDriver(Long driverId) {
        driverRepository.setAssigned(driverId);
        LOGGER.info("Driver with id {} was assigned to vehicle", driverId);
    }

    @Override
    @Transactional(readOnly = true)
    public Vehicle getVehicleOfAuthenticatedDriver() {
        Driver driver = driverService.getAuthenticatedDriver();
        VehicleEntity vehicle = vehicleRepository.findVehicleByDriver(DriverMapper.mapModelToEntity(driver));
        return VehicleMapper.mapEntityToModel(vehicle);
    }

    @Override
    @Transactional
    public void setBroken(Long id) {
        vehicleRepository.setBroken(id);
        LOGGER.info("Vehicle with id = {} has broken", id);
    }

    @Override
    @Transactional
    public void repaired(Long id) {
        VehicleEntity vehicle = vehicleRepository.getOne(id);
        if (isNotEmpty(vehicle.getLoads()) && vehicle.getLoads().iterator().next().getStatus() == IN_PROGRESS) {
            vehicleRepository.setWorking(id);
            LOGGER.info("Vehicle with id = {} was repaired and is continuing delivery", id);

        } else {
            vehicle.setStatus(FREE);
            vehicleRepository.saveAndFlush(vehicle);
            LOGGER.info("Vehicle with id = {} was repaired and is free now", id);
        }
    }

    @Override
    @Transactional
    public void allLoadsDelivered(Long id) {
        VehicleEntity vehicleEntity = vehicleRepository.getOne(id);
        vehicleEntity.getLoads().forEach(loadEntity -> {
            HistoryEntity historyEntity = new HistoryEntity();
            historyEntity.setVehicle(loadEntity.getVehicle());
            historyEntity.setPrimaryDriver(loadEntity.getVehicle().getPrimaryDriver());
            historyEntity.setCoDriver(loadEntity.getVehicle().getCoDriver());
            historyRepository.saveAndFlush(historyEntity);
            loadEntity.setHistory(historyEntity);
            loadRepository.saveAndFlush(loadEntity);
        });
        loadRepository.setDone(vehicleEntity);
        freeVehicleAndDrivers(vehicleEntity);
        LOGGER.info("Vehicle with id = {} completed delivery", id);
    }

    @Override
    @Transactional
    public void rejectDelivery(Long id) {
        VehicleEntity vehicleEntity = vehicleRepository.getOne(id);
        loadRepository.setRejected(vehicleEntity);
        freeVehicleAndDrivers(vehicleEntity);
        LOGGER.info("Vehicle with id = {} rejected delivery", id);
    }
}
