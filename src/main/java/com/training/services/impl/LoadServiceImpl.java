package com.training.services.impl;

import com.training.entities.LoadEntity;
import com.training.entities.VehicleEntity;
import com.training.entities.enums.LoadStatus;
import com.training.models.Load;
import com.training.repositories.LoadRepository;
import com.training.services.LoadService;
import com.training.services.VehicleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.training.mappers.LoadMapper.mapEntityListToModelList;
import static com.training.mappers.LoadMapper.mapEntityToModel;
import static com.training.mappers.LoadMapper.mapModelToEntity;

@Service
public class LoadServiceImpl implements LoadService {

    private final static Logger LOGGER = LogManager.getLogger(LoadServiceImpl.class);

    @Autowired
    private LoadRepository loadRepository;

    @Autowired
    private VehicleService vehicleService;

    @Override
    @Transactional(readOnly = true)
    public Load find(Long id) {
        Load load = mapEntityToModel(loadRepository.getOne(id));
        LOGGER.info("Got load with id = {}", load.getId());
        return load;
    }

    @Override
    @Transactional
    public void save(Load load) {
        LoadEntity loadEntity = mapModelToEntity(load);
        loadRepository.saveAndFlush(loadEntity);
        if (load.getId() == null) {
            LOGGER.info("Created load with id = {}", loadEntity.getId());
        } else {
            LOGGER.info("Updated load with id = {}", loadEntity.getId());
        }
        mapEntityToModel(loadEntity);
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
        List<Load> loads = mapEntityListToModelList(loadRepository.findAll());
        LOGGER.info("Found all loads");
        return loads;
    }

    @Override
    @Transactional
    public void deleteVehicleFromLoad(Long id) {

        // TODO change to repository method
        LoadEntity loadEntity = loadRepository.getOne(id);
        VehicleEntity vehicleEntity = loadEntity.getVehicle();
        if (vehicleEntity != null) {
            vehicleEntity.getLoads().remove(loadEntity);
            vehicleService.checkIfCompletedDelivery(vehicleEntity);
            loadEntity.setVehicle(null);
            loadEntity.setStatus(LoadStatus.DONE);
            loadRepository.saveAndFlush(loadEntity);
        }
    }
}
