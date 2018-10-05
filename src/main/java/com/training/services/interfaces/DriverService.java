package com.training.services.interfaces;

import com.training.entities.DriverEntity;
import com.training.services.BaseService;

import java.util.List;

public interface DriverService extends BaseService<DriverEntity, Long> {

    List<DriverEntity> getAll();
}
