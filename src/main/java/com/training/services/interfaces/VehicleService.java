package com.training.services.interfaces;

import com.training.entities.VehicleEntity;
import com.training.services.BaseService;

import java.util.List;

public interface VehicleService extends BaseService<VehicleEntity, Long> {

    List<VehicleEntity> getAll();
}
