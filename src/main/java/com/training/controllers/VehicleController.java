package com.training.controllers;

import com.training.entities.DriverEntity;
import com.training.entities.VehicleEntity;
import com.training.models.Vehicle;
import com.training.services.interfaces.VehicleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping("/vehicles")
    public ModelAndView allVehiclesView() {
        List<Vehicle> vehicles = vehicleService.getAll();
        return new ModelAndView("vehiclesView", "vehicle", vehicles);
    }

    @GetMapping(value = "/addVehicle")
    public ModelAndView getAddDriverPage() {
        return new ModelAndView("addVehicleView", "newVehicle", new VehicleEntity());
    }

    @PostMapping(value="/addVehicle")
    public ModelAndView addVehicle(@ModelAttribute("newVehicle") Vehicle newVehicle){
        vehicleService.create(newVehicle);
        return new ModelAndView("redirect:/vehicles");
    }

    @GetMapping(value = "/removeVehicle/{id}")
    public ModelAndView deleteVehicle(@PathVariable("id") Long id){
        vehicleService.remove(id);
        return new ModelAndView("redirect:/vehicles");
    }
}
