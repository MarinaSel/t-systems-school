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

import java.util.Iterator;
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
        return vehicles;
    }

    @Transactional
    public Vehicle findByRegistrationNumber(String registrationNumber) {
        return getModelFromEntity(vehicleRepository.findVehicleEntityByRegistrationNumber(registrationNumber));
    }
}
