package com.training.services;

import com.training.entities.VehicleEntity;
import com.training.models.Vehicle;
import com.training.services.BaseService;

import java.util.List;

public interface VehicleService extends BaseService<Vehicle, Long> {

    List<Vehicle> getAll();

    List<Vehicle> getAllFreeWithNecessaryCapacityAndDrivers(Integer necessaryCapacity);

    Vehicle findByRegistrationNumber(String registrationNumber);

    void checkVehicleIfEndedDelivery(VehicleEntity vehicleEntity);

    Vehicle changeVehicleStatusForBeginDelivery(Long id);
}
