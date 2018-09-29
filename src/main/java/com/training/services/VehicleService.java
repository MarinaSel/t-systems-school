package com.training.services;

import com.training.model.entities.VehicleEntity;

public interface VehicleService {

    VehicleEntity get(Long id);

    VehicleEntity create(VehicleEntity vehicleEntity);

    VehicleEntity update(VehicleEntity vehicleEntity);

    void delete(VehicleEntity vehicleEntity);
}
