package com.training.services.impl;

import com.training.entities.VehicleEntity;
import com.training.mappers.VehicleMapper;
import com.training.models.Vehicle;
import com.training.repositories.VehicleRepository;
import com.training.services.interfaces.VehicleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Transactional
    public Vehicle get(Long id){
        return VehicleMapper.getModelFromEntity(vehicleRepository.getOne(id));
    }

    @Transactional
    public void create(Vehicle vehicle){ vehicleRepository.saveAndFlush(VehicleMapper.getEntityFromModel(vehicle));
    }

    @Transactional
    public Vehicle update(Vehicle vehicle){
        VehicleEntity vehicleEntity = vehicleRepository.saveAndFlush(VehicleMapper.getEntityFromModel(vehicle));
        return VehicleMapper.getModelFromEntity(vehicleEntity);
    }

    @Transactional
    public void remove(Long id){
        vehicleRepository.deleteById(id);
    }

    @Transactional
    public List<Vehicle> getAll() {
        return VehicleMapper.getModelListFromEntityList(vehicleRepository.findAll());
    }
}
