package com.training.controllers;

import com.training.model.entities.DriverEntity;
import com.training.services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;


@Controller
public class DriverController {

    @Autowired
    private DriverService driverService;

    @RequestMapping(value = "drivers", method = RequestMethod.GET)
    public String AllDriversView(Model model){
        List<DriverEntity> drivers = driverService.getDrivers();
        model.addAttribute("drivers", drivers);
        return "driversView";
    }
   @RequestMapping(value = "/addNewDriver", method = RequestMethod.GET)
   public String redirectAddNewDriver() {
        return "addDriver";
   }

    @RequestMapping(value = "/addDriver", method = RequestMethod.POST)
    public void addDriver() {
    }
}
