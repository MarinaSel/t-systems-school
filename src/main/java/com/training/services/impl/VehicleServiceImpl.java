package com.training.services.impl;

import com.training.entities.DriverEntity;
import com.training.entities.LoadEntity;
import com.training.entities.VehicleEntity;
import com.training.entities.enums.DriverStatus;
import com.training.entities.enums.LoadStatus;
import com.training.entities.enums.VehicleStatus;
import com.training.models.Load;
import com.training.models.Vehicle;
import com.training.repositories.DriverRepository;
import com.training.repositories.VehicleRepository;
import com.training.services.VehicleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static com.training.mappers.VehicleMapper.mapEntityListToModelList;
import static com.training.mappers.VehicleMapper.mapEntityToModel;
import static com.training.mappers.VehicleMapper.mapModelToEntity;
import static org.apache.commons.collections4.CollectionUtils.isEmpty;

@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {

    private final static Logger LOGGER = LogManager.getLogger(VehicleServiceImpl.class);

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Override
    public Vehicle get(Long id) {
        Vehicle vehicle = mapEntityToModel(vehicleRepository.getOne(id));
        LOGGER.info("Found vehicle with id = {}", vehicle.getId());
        return vehicle;
    }

    @Override
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
    public void remove(Long id) {
        vehicleRepository.deleteById(id);
        LOGGER.info("Deleted vehicle with id = {}", id);
    }

    @Override
    public List<Vehicle> getAll() {
        List<Vehicle> vehicles = mapEntityListToModelList(vehicleRepository.findAll());
        LOGGER.info("Found all vehicles");
        return vehicles;
    }

    @Override
    public List<Vehicle> getAllFreeWithNecessaryCapacity(Integer necessaryCapacity) {
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
    public Vehicle findByRegistrationNumber(String registrationNumber) {
        Vehicle vehicle = mapEntityToModel(vehicleRepository.findVehicleEntityByRegistrationNumber(registrationNumber));
        LOGGER.info("Found vehicle by registration number = {}", registrationNumber);
        return vehicle;
    }

    @Override
    public void checkIfCompletedDelivery(VehicleEntity vehicleEntity) {
        if (isEmpty(vehicleEntity.getLoads())) {
            DriverEntity primaryDriver = vehicleEntity.getPrimaryDriver();
            DriverEntity coDriver = vehicleEntity.getCoDriver();

            primaryDriver.setStatus(DriverStatus.FREE);
            coDriver.setStatus(DriverStatus.FREE);

            driverRepository.saveAndFlush(primaryDriver);
            driverRepository.saveAndFlush(coDriver);

            vehicleEntity.setPrimaryDriver(null);
            vehicleEntity.setCoDriver(null);
            vehicleEntity.setStatus(VehicleStatus.FREE);

            vehicleRepository.saveAndFlush(vehicleEntity);
        }
    }

    @Override
    public void changeStatusWhenStartingDelivery(Long id) {
        VehicleEntity vehicleEntity = vehicleRepository.getOne(id);
        if (!isEmpty(vehicleEntity.getLoads())) {
            vehicleEntity.setStatus(VehicleStatus.WORKING);
            Set<LoadEntity> loads = vehicleEntity.getLoads();
            for (LoadEntity load : loads) {
                load.setStatus(LoadStatus.IN_PROGRESS);
            }
            vehicleRepository.saveAndFlush(vehicleEntity);
        }
    }
}
