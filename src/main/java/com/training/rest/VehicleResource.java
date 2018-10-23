package com.training.rest;

import com.training.models.Vehicle;
import com.training.services.VehicleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/vehicle")
public class VehicleResource {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping
    public List<Vehicle> getVehicles(@RequestParam(value = "weight") Integer weight) {
        return vehicleService.getAllFreeWithNecessaryCapacityAndDrivers(weight);
    }
}
