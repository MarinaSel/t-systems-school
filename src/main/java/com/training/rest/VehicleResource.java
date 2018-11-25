package com.training.rest;

import com.training.models.Vehicle;
import com.training.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/vehicle")
public class VehicleResource {

    private final VehicleService vehicleService;

    @Autowired
    public VehicleResource(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping
    public List<Vehicle> getVehicles(@RequestParam(value = "weight") Integer weight) {
        return vehicleService.findAllFreeWithNecessaryCapacity(weight);
    }

    @GetMapping("/findByRegistrationNumber/{registrationNumber}")
    public Vehicle findByRegistrationNumber(@PathVariable("registrationNumber") String registrationNumber) {
        return vehicleService.findByRegistrationNumber(registrationNumber);
    }
}
