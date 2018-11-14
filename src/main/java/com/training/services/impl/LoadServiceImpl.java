package com.training.services.impl;

import com.training.entities.LoadEntity;
import com.training.entities.VehicleEntity;
import com.training.mappers.LoadMapper;
import com.training.models.Load;
import com.training.models.Vehicle;
import com.training.repositories.LoadRepository;
import com.training.services.LoadService;
import com.training.services.VehicleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.training.entities.enums.LoadStatus.ASSIGNED;

@Service
public class LoadServiceImpl implements LoadService {

    private final static Logger LOGGER = LogManager.getLogger(LoadServiceImpl.class);

    private final LoadRepository loadRepository;

    private final VehicleService vehicleService;

    @Autowired
    public LoadServiceImpl(LoadRepository loadRepository, VehicleService vehicleService) {
        this.loadRepository = loadRepository;
        this.vehicleService = vehicleService;
    }

    @Override
    @Transactional(readOnly = true)
    public Load find(Long id) {
        Load load = LoadMapper.mapEntityToModel(loadRepository.getOne(id));
        LOGGER.info("Found load with id = {}", load.getId());
        return load;
    }

    @Override
    @Transactional
    public void save(Load load) {
        LoadEntity loadEntity = LoadMapper.mapModelToEntity(load);
        loadRepository.saveAndFlush(loadEntity);
        if (load.getId() == null) {
            LOGGER.info("Created load with id = {}", loadEntity.getId());
        } else {
            LOGGER.info("Updated load with id = {}", loadEntity.getId());
        }
        LoadMapper.mapEntityToModel(loadEntity);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        loadRepository.deleteById(id);
        LOGGER.info("Deleted load with id = {}", id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Load> findAll() {
        List<Load> loads = LoadMapper.mapEntityListToModelList(loadRepository.findAll());
        LOGGER.info("Found all loads");
        return loads;
    }

    @Override
    @Transactional
    public void saveAssignedLoad(Load load, String registrationNumber, String primaryDriverLicense,
                                 String coDriverLicense) {
        if (load.getStatus() == ASSIGNED) {
            deleteVehicleFromLoad(load.getId());
        } else {
            load.setStatus(ASSIGNED);
        }
        Vehicle vehicle = vehicleService.findByRegistrationNumber(registrationNumber);
        load.setVehicle(vehicle);

        LoadEntity loadEntity = LoadMapper.mapModelToEntity(load);
        loadRepository.saveAndFlush(loadEntity);
        LOGGER.info("Load with id {} was saved and assigned to vehicle with id {}", loadEntity.getId(),
                vehicle.getId());
        vehicleService.assignToDrivers(vehicle, primaryDriverLicense, coDriverLicense);
    }

    @Override
    @Transactional
    public void deliverLoad(Long loadId) {
        deleteVehicleFromLoad(loadId);
        loadRepository.setDone(loadId);
        LOGGER.info("Load with id {} delivered", loadId);
    }

    @Transactional
    void deleteVehicleFromLoad(Long loadId) {
        LoadEntity loadEntity = loadRepository.getOne(loadId);
        VehicleEntity vehicleEntity = loadEntity.getVehicle();
        vehicleEntity.getLoads().remove(loadEntity);
        vehicleService.checkIfVehicleIsEmpty(vehicleEntity);
    }
}
