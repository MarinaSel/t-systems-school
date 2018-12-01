package com.training.rest;

import com.training.models.Load;
import com.training.models.Vehicle;
import com.training.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

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

    @GetMapping("/findByRegistrationNumber")
    public Vehicle findByRegistrationNumber(@RequestParam("registrationNumber") String registrationNumber,
                                            @RequestParam("vehicleId") Long id) {
        Vehicle vehicle = vehicleService.findByRegistrationNumber(registrationNumber);
        if (vehicle != null && vehicle.getId().equals(id)) {
            return null;
        }
        return vehicle;
    }

    @GetMapping("/findVehicleWithLoads")
    public Set<Load> getVehicleOfAuthenticatedDriver() {
        Vehicle vehicle = vehicleService.getVehicleOfAuthenticatedDriver();
        return vehicle.getLoads();
    }
}
