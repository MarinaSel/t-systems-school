package com.training.services.impl;

import com.training.entities.VehicleEntity;
import com.training.entities.enums.VehicleStatus;
import com.training.models.Load;
import com.training.models.Vehicle;
import com.training.repositories.VehicleRepository;
import com.training.services.interfaces.VehicleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.training.mappers.VehicleMapper.getEntityFromModel;
import static com.training.mappers.VehicleMapper.getModelFromEntity;
import static com.training.mappers.VehicleMapper.getModelListFromEntityList;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Transactional
    public Vehicle get(Long id){
        return getModelFromEntity(vehicleRepository.getOne(id));
    }

    @Transactional
    public void create(Vehicle vehicle){ vehicleRepository.saveAndFlush(getEntityFromModel(vehicle));
    }

    @Transactional
    public Vehicle update(Vehicle vehicle){
        VehicleEntity vehicleEntity = vehicleRepository.saveAndFlush(getEntityFromModel(vehicle));
        return getModelFromEntity(vehicleEntity);
    }

    @Transactional
    public void remove(Long id){
        vehicleRepository.deleteById(id);
    }

    @Transactional
    public List<Vehicle> getAll() {
        return getModelListFromEntityList(vehicleRepository.findAll());
    }

    @Transactional
    public List<Vehicle> getAllFreeWithNecessaryCapacityAndDrivers(Integer necessaryCapacity) {
        List<Vehicle> vehicles = getModelListFromEntityList(vehicleRepository.findAllByStatusAndCapacityGreaterThanEqual(
                VehicleStatus.FREE, necessaryCapacity));

        for (Vehicle vehicle : vehicles) {
            if (vehicle.getDrivers().size() == 0){
                vehicles.remove(vehicle);
                continue;
            }
            int sumWeight = 0;

            for (Load load : vehicle.getLoads()) {
                sumWeight += load.getWeight();
            }
            if (necessaryCapacity > vehicle.getCapacity() - sumWeight){
                vehicles.remove(vehicle);
            }
        }
        return vehicles;
    }

    @Override
    public Vehicle findByRegistrationNumber(String registrationNumber) {
        return getModelFromEntity(vehicleRepository.findVehicleEntityByRegistrationNumber(registrationNumber));
    }
}
