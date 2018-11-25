package com.training.services.impl;

import com.training.entities.DriverEntity;
import com.training.entities.LoadEntity;
import com.training.entities.VehicleEntity;
import com.training.mappers.LoadMapper;
import com.training.mappers.VehicleMapper;
import com.training.models.Load;
import com.training.repositories.DriverRepository;
import com.training.repositories.LoadRepository;
import com.training.services.LoadService;
import com.training.services.VehicleService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.training.entities.enums.LoadStatus.ASSIGNED;

@Service
public class LoadServiceImpl implements LoadService {

    private final static Logger LOGGER = LogManager.getLogger(LoadServiceImpl.class);

    private final LoadRepository loadRepository;
    private final DriverRepository driverRepository;
    private final VehicleService vehicleService;

    @Autowired
    public LoadServiceImpl(LoadRepository loadRepository, DriverRepository driverRepository,
                           VehicleService vehicleService) {
        this.loadRepository = loadRepository;
        this.driverRepository = driverRepository;
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
            VehicleEntity previousVehicle = loadRepository.getOne(load.getId()).getVehicle();
            if (previousVehicle.getRegistrationNumber().equals(registrationNumber)) {
                checkVehicle(previousVehicle, primaryDriverLicense, coDriverLicense);
            } else {
                deleteVehicleFromLoad(load.getId());
            }
        } else {
            load.setStatus(ASSIGNED);
        }
        VehicleEntity newVehicle = VehicleMapper.mapModelToEntity(
                vehicleService.findByRegistrationNumber(registrationNumber));
        checkVehicle(newVehicle, primaryDriverLicense, coDriverLicense);

        LoadEntity loadEntity = LoadMapper.mapModelToEntity(load);
        loadEntity.setVehicle(newVehicle);
        loadRepository.saveAndFlush(loadEntity);
        LOGGER.info("Load with id {} was saved and assigned to vehicle with id {}", loadEntity.getId(),
                newVehicle.getId());
        vehicleService.assignToDrivers(newVehicle, primaryDriverLicense, coDriverLicense);
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

        if (CollectionUtils.isEmpty(vehicleEntity.getLoads())) {
            vehicleService.freeVehicleAndDrivers(vehicleEntity);
            LOGGER.info("Vehicle with id = {} completed delivery", vehicleEntity.getId());
        }
    }

    @Transactional
    void checkVehicle(VehicleEntity vehicleEntity, String primaryDriverLicense, String coDriverLicense) {
        checkDriver(vehicleEntity.getPrimaryDriver(), primaryDriverLicense);
        checkDriver(vehicleEntity.getCoDriver(), coDriverLicense);
    }

    @Transactional
    void checkDriver(DriverEntity previousDriver, String newDriverLicense) {
        if (previousDriver != null && !previousDriver.getDrivingLicenseNum().equals(newDriverLicense) &&
                !StringUtils.isEmpty(newDriverLicense)) {
            driverRepository.setFree(previousDriver.getId());
        }
    }
}
