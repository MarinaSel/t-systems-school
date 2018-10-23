package com.training.services;

import com.training.models.Driver;
import com.training.services.BaseService;

import java.util.List;
import java.util.Set;

public interface DriverService extends BaseService<Driver, Long> {

    List<Driver> getAll();

    List<Driver> getAllFree();

    Driver findByDrivingLicenseNum(String drivingLicenseNum);

}
