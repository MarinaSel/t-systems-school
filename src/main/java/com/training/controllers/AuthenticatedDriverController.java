package com.training.controllers;

import com.training.models.Driver;
import com.training.models.Vehicle;
import com.training.services.DriverService;
import com.training.services.LoadService;
import com.training.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/home")
public class AuthenticatedDriverController {

    private final DriverService driverService;
    private final VehicleService vehicleService;
    private final LoadService loadService;

    @Autowired
    public AuthenticatedDriverController(DriverService driverService, VehicleService vehicleService, LoadService loadService) {
        this.driverService = driverService;
        this.vehicleService = vehicleService;
        this.loadService = loadService;
    }

    @GetMapping("/profile")
    public ModelAndView getDriver() {
        Driver driver = driverService.getAuthenticatedDriver();
        Vehicle vehicle = vehicleService.getVehicleOfAuthenticatedDriver();
        return new ModelAndView("authenticatedDriverPage").addObject("driver", driver)
                .addObject("vehicle", vehicle);
    }

    @GetMapping("/delivered/{id}")
    public ModelAndView deliveredLoad(@PathVariable("id") Long id) {
        loadService.deliverLoad(id);
        return new ModelAndView("redirect:/home/profile");
    }

    @GetMapping("/brokenVehicle/{id}")
    public ModelAndView brokenVehicle(@PathVariable("id") Long id) {
        vehicleService.setBroken(id);
        return new ModelAndView("redirect:/home/profile");
    }

    @GetMapping("/repairedVehicle/{id}")
    public ModelAndView repairedVehicle(@PathVariable("id") Long id) {
        vehicleService.repaired(id);
        return new ModelAndView("redirect:/home/profile");
    }

    @GetMapping("/restDriver/{id}")
    public ModelAndView restDriver(@PathVariable("id") Long id) {
        driverService.setRest(id);
        return new ModelAndView("redirect:/home/profile");
    }

    @GetMapping("/freeDriver/{id}")
    public ModelAndView freeDriver(@PathVariable("id") Long id) {
        driverService.setFree(id);
        return new ModelAndView("redirect:/home/profile");
    }

    @GetMapping("/beginDelivery/{id}")
    public ModelAndView beginDelivery(@PathVariable("id") Long id) {
        vehicleService.startDelivery(id);
        return new ModelAndView("redirect:/home/profile");
    }

    @GetMapping("/allDelivered/{id}")
    public ModelAndView allLoadsDelivered(@PathVariable("id") Long id) {
        vehicleService.allLoadsDelivered(id);
        return new ModelAndView("redirect:/home/profile");
    }

    @GetMapping("/rejectDelivery/{id}")
    public ModelAndView rejectDelivery(@PathVariable("id") Long id) {
        vehicleService.rejectDelivery(id);
        return new ModelAndView("redirect:/home/profile");
    }
}
