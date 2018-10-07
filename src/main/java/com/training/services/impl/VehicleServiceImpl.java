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

    @Transactional
    public VehicleEntity get(Long id){
        return vehicleRepository.getOne(id);
    }

    @Transactional
    public void create(VehicleEntity vehicleEntity){ vehicleRepository.saveAndFlush(vehicleEntity);
    }

    @Transactional
    public VehicleEntity update(VehicleEntity vehicleEntity){
        return vehicleRepository.saveAndFlush(vehicleEntity);
    }

    @Transactional
    public void remove(Long id){
        vehicleRepository.deleteById(id);
    }

    @Transactional
    public List<VehicleEntity> getAll() {
        return vehicleRepository.findAll();
    }
}
