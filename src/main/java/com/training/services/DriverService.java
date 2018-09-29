package com.training.services;

import com.training.model.entities.DriverEntity;

import java.util.List;

public interface DriverService {

    DriverEntity get(Long id);

    DriverEntity create(DriverEntity driverEntity);

    DriverEntity update(DriverEntity driverEntity);

    void delete(DriverEntity driverEntity);

    List<DriverEntity> findAll();
}
