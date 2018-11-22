package com.training.controllers;

import com.training.entities.enums.DriverStatus;
import com.training.models.Driver;
import com.training.services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/driver")
public class DriverController {

    private final DriverService driverService;

    @Autowired
    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping("/editDriver/{id}")
    public ModelAndView editDriver(@PathVariable("id") Long id, ModelAndView modelAndView) {
        Driver driverToEdit = driverService.find(id);
        modelAndView.addObject("statuses", DriverStatus.values());
        modelAndView.addObject("editableDriver", driverToEdit);
        modelAndView.setViewName("saveDriverPage");
        return modelAndView;
    }

    @GetMapping("/addDriver")
    public ModelAndView getAddDriverPage(ModelAndView modelAndView) {
        modelAndView.addObject("editableDriver", new Driver()).setViewName("saveDriverPage");
        return modelAndView;
    }

    @PostMapping("/saveDriver")
    public ModelAndView saveDriver(@ModelAttribute("editableDriver") Driver driver) {
        driverService.save(driver);
        return new ModelAndView("redirect:/driver/allDrivers");
    }

    @GetMapping("/allDrivers")
    public ModelAndView getAllDriversPage() {
        List<Driver> drivers = driverService.findAll();
        return new ModelAndView("driversPage").addObject("drivers", drivers);
    }

    @GetMapping("/fireDriver/{id}")
    public ModelAndView fireDriver(@PathVariable("id") Long id) {
        driverService.fireDriver(id);
        return new ModelAndView("redirect:/driver/allDrivers");
    }
}

