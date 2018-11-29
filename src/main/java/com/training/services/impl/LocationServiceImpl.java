package com.training.services.impl;

import com.training.entities.LocationEntity;
import com.training.mappers.LocationMapper;
import com.training.models.Location;
import com.training.repositories.LocationRepository;
import com.training.services.LocationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    private final static Logger LOGGER = LogManager.getLogger(LoadServiceImpl.class);

    private final LocationRepository locationRepository;

    @Autowired
    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public LocationEntity find(Long id) {
        return locationRepository.getOne(id);
    }

    @Transactional
    public void save(LocationEntity locationEntity) {
        locationRepository.saveAndFlush(locationEntity);
    }

    @Override
    public List<Location> findAll() {
        List<Location> locations = LocationMapper.mapEntityListToModelList(locationRepository.findAll());
        LOGGER.info("Found all locations");
        return locations;
    }

    @Override
    @Transactional(readOnly = true)
    public Location findByName(String name) {
        Location location = LocationMapper.mapEntityToModel(locationRepository.findByName(name));
        LOGGER.info("Found location by name ={}", name);
        return location;
    }
}
