package com.training.services.interfaces;

import com.training.model.entities.DriverEntity;
import com.training.services.BaseService;

import java.util.List;

public interface DriverService extends BaseService<DriverEntity, Long> {

    List<DriverEntity> getDrivers();
}
