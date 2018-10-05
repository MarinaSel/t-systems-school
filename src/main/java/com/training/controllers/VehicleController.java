package com.training.controllers;

import com.training.entities.VehicleEntity;
import com.training.services.interfaces.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @RequestMapping("/vehiclesView")
    public ModelAndView allVehiclesView() {
        List<VehicleEntity> vehicles = vehicleService.getAll();
        return new ModelAndView("vehiclesView", "vehicle", vehicles);
    }

    @RequestMapping(value = "/vehicle", method = RequestMethod.GET)
    public ModelAndView addVehicleView() {
        return new ModelAndView("addVehicle", "newVehicle", new VehicleEntity());
    }

    @RequestMapping(value="/addVehicle",method = RequestMethod.POST)
    public ModelAndView addVehicle(@ModelAttribute("newVehicle") VehicleEntity newVehicle){
        vehicleService.create(newVehicle);
        return new ModelAndView("redirect:/vehiclesView");
    }

    @RequestMapping(value = "/removeVehicle/{id}")
    public ModelAndView deleteVehicle(@PathVariable("id") Long id){
        vehicleService.remove(id);
        return new ModelAndView("redirect:/vehiclesView");
    }
}
