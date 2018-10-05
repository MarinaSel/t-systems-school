package com.training.services.impl;

import com.training.entities.VehicleEntity;
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

    @Override
    @Transactional
    public VehicleEntity get(Long id){
        return vehicleRepository.getOne(id);
    }

    @Override
    @Transactional
    public VehicleEntity create(VehicleEntity vehicleEntity){
        return vehicleRepository.saveAndFlush(vehicleEntity);
    }

    @Override
    @Transactional
    public VehicleEntity update(VehicleEntity vehicleEntity){
        return vehicleRepository.saveAndFlush(vehicleEntity);
    }

    @Override
    @Transactional
    public void remove(Long id){
        vehicleRepository.deleteById(id);
    }

    @Override
    @Transactional
    public List<VehicleEntity> getAll() {
        return vehicleRepository.findAll();
    }
}
