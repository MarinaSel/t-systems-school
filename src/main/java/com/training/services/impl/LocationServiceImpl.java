package com.training.services.impl;

import com.training.entities.LocationEntity;
import com.training.repositories.LocationRepository;
import com.training.services.interfaces.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Override
    public LocationEntity get(Long id){
        return locationRepository.getOne(id);
    }

    @Override
    public LocationEntity create(LocationEntity locationEntity){
        return locationRepository.saveAndFlush(locationEntity);
    }

    @Override
    public LocationEntity update(LocationEntity locationEntity){
        return locationRepository.saveAndFlush(locationEntity);
    }

    @Override
    public void remove(Long id){
        locationRepository.deleteById(id);
    }


}
