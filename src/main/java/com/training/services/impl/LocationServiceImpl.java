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

    @Override
    @Transactional(readOnly = true)
    public Location find(Long id) {
        Location location = LocationMapper.mapEntityToModel(locationRepository.getOne(id));
        LOGGER.info("Found location with id = {}", location.getId());
        return location;
    }

    @Override
    @Transactional
    public void save(Location location) {
        LocationEntity locationEntity = LocationMapper.mapModelToEntity(location);
        locationRepository.saveAndFlush(locationEntity);
        if (location.getId() == null) {
            LOGGER.info("Created location with id = {}", locationEntity.getId());
        } else {
            LOGGER.info("Updated location with id = {}", locationEntity.getId());
        }
        LocationMapper.mapEntityToModel(locationEntity);
        ;
    }

    @Override
    @Transactional(readOnly = true)
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
