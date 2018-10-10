package com.training.services.interfaces;

import com.training.models.Vehicle;
import com.training.services.BaseService;

import java.util.List;

public interface VehicleService extends BaseService<Vehicle, Long> {

    List<Vehicle> getAll();

    List<Vehicle> getAllFreeWithNecessaryCapacity(Integer capacity);
}
