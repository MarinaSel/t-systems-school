package com.training.rest;

import com.training.models.Driver;
import com.training.services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/driver")
public class DriversResource {

    @Autowired
    private DriverService driverService;

    @GetMapping
    public List<Driver> getDrivers() {
        return driverService.getAllFree();
    }
}
