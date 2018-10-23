package com.training.services.impl;

import com.training.entities.DriverEntity;
import com.training.entities.VehicleEntity;
import com.training.entities.enums.DriverStatus;
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

import static com.training.mappers.VehicleMapper.getEntityFromModel;
import static com.training.mappers.VehicleMapper.getModelFromEntity;
import static com.training.mappers.VehicleMapper.getModelListFromEntityList;

@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {

    private final static Logger logger = LogManager.getLogger(VehicleServiceImpl.class);

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Override
    public Vehicle get(Long id){
        Vehicle vehicle = getModelFromEntity(vehicleRepository.getOne(id));
        logger.info("Found vehicle with id = {}", vehicle.getId());
        return vehicle;
    }

    @Override
    public Vehicle save(Vehicle vehicle){
        VehicleEntity vehicleEntity = getEntityFromModel(vehicle);
        vehicleRepository.saveAndFlush(vehicleEntity);

        if(vehicle.getId() == null){
            logger.info("Created vehicle with id = {}", vehicleEntity.getId());
        }
        else{
            logger.info("Updated vehicle with id = {}", vehicle.getId());
        }
        return getModelFromEntity(vehicleEntity);
    }

    @Override
    public void remove(Long id){
        vehicleRepository.deleteById(id);
        logger.info("Deleted vehicle with id = {}", id);
    }

    @Override
    public List<Vehicle> getAll() {
        List<Vehicle> vehicles = getModelListFromEntityList(vehicleRepository.findAll());
        logger.info("Found all vehicles");
        return vehicles;
    }

    @Override
    public List<Vehicle> getAllFreeWithNecessaryCapacityAndDrivers(Integer necessaryCapacity) {
        List<Vehicle> vehicles = getModelListFromEntityList(vehicleRepository.findAllByStatus(VehicleStatus.FREE));
        Iterator<Vehicle> iterator = vehicles.iterator();

        while (iterator.hasNext()){
            Vehicle vehicle = iterator.next();

            int sumWeight = 0;
            for (Load load : vehicle.getLoads()) {
                sumWeight += load.getWeight();
            }

            if ((vehicle.getCapacity() - sumWeight < necessaryCapacity)){
                iterator.remove();
            }
        }
        logger.info("Found all vehicles with capacity = {} and status FREE", necessaryCapacity);
        return vehicles;
    }

    @Override
    public Vehicle findByRegistrationNumber(String registrationNumber) {
        Vehicle vehicle = getModelFromEntity(vehicleRepository.findVehicleEntityByRegistrationNumber(registrationNumber));
        logger.info("Found vehicle by registration number = {}", registrationNumber);
        return vehicle;
    }

    @Override
    public void checkVehicleIfEndedDelivery(VehicleEntity vehicleEntity){
        if(vehicleEntity.getLoads() == null || vehicleEntity.getLoads().isEmpty()){
            Set<DriverEntity> drivers = vehicleEntity.getDrivers();
            for (DriverEntity driverEntity : drivers) {
                driverEntity.setStatus(DriverStatus.FREE);
                driverEntity.setVehicle(null);
                driverRepository.saveAndFlush(driverEntity);
            }
            vehicleEntity.getDrivers().clear();
            vehicleEntity.setStatus(VehicleStatus.FREE);
            vehicleRepository.saveAndFlush(vehicleEntity);
        }
    }

    @Override
    public Vehicle changeVehicleStatusForBeginDelivery(Long id) {
        VehicleEntity vehicleEntity = vehicleRepository.getOne(id);
        if (vehicleEntity.getLoads() != null && !vehicleEntity.getLoads().isEmpty()) {
            vehicleEntity.setStatus(VehicleStatus.WORKING);
            vehicleRepository.saveAndFlush(vehicleEntity);
        }
        return getModelFromEntity(vehicleEntity);
    }

}
