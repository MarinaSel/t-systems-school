package com.training.services.interfaces;

import com.training.models.Driver;
import com.training.services.BaseService;

import java.util.List;

public interface DriverService extends BaseService<Driver, Long> {

    List<Driver> getAll();

}
