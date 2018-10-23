package com.training.services.impl;

import com.training.entities.LocationEntity;
import com.training.repositories.LocationRepository;
import com.training.services.LocationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationRepository locationRepository;

    public LocationEntity get(Long id){
        return locationRepository.getOne(id);
    }

    @Transactional
    public LocationEntity save(LocationEntity locationEntity){
        return locationRepository.saveAndFlush(locationEntity);
    }

    @Transactional
    public void remove(Long id){
        locationRepository.deleteById(id);
    }


}
