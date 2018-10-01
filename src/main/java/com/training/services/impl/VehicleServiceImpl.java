package com.training.services.impl;

import com.training.model.entities.VehicleEntity;
import com.training.repositories.VehicleRepository;
import com.training.services.interfaces.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public VehicleEntity get(Long id){
        return vehicleRepository.getOne(id);
    }

    @Override
    public VehicleEntity create(VehicleEntity vehicleEntity){
        return vehicleRepository.saveAndFlush(vehicleEntity);
    }

    @Override
    public VehicleEntity update(VehicleEntity vehicleEntity){
        return vehicleRepository.saveAndFlush(vehicleEntity);
    }

    @Override
    public void delete(VehicleEntity vehicleEntity){
        vehicleRepository.delete(vehicleEntity);
    }

}
