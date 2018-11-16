package com.training.controllers;

import com.training.models.Driver;
import com.training.models.Vehicle;
import com.training.services.DriverService;
import com.training.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AuthenticatedDriverController {

    private final DriverService driverService;

    private final VehicleService vehicleService;

    @Autowired
    public AuthenticatedDriverController(DriverService driverService, VehicleService vehicleService) {
        this.driverService = driverService;
        this.vehicleService = vehicleService;
    }

    @GetMapping("/home")
    public ModelAndView getDriver() {
        Driver driver = driverService.getAuthenticatedDriver();
        Vehicle vehicle = vehicleService.getVehicleOfAuthenticatedDriver();
        return new ModelAndView("authenticatedDriverPage").addObject("driver", driver)
                .addObject("vehicle", vehicle);
    }
}
