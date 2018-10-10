package com.training.controllers;

import com.training.models.Vehicle;
import com.training.services.interfaces.VehicleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("/editVehicle/{id}")
    public ModelAndView getVehicleById(@PathVariable("id") Long id, RedirectAttributes redirectAttributes){
        Vehicle vehicleToEdit = vehicleService.get(id);
        redirectAttributes.addFlashAttribute("editableVehicle", vehicleToEdit);
        return new ModelAndView("redirect:/getAddVehicleView");
    }

    @GetMapping(value = "/getAddVehicleView")
    public ModelAndView getAddVehiclePage(Model model) {
        return new ModelAndView("addVehicleView", model.asMap());
    }

    @PostMapping(value="/addVehicle")
    public ModelAndView addVehicle(@ModelAttribute("editableVehicle") Vehicle vehicle){
        vehicleService.create(vehicle);
        return new ModelAndView("redirect:/vehicles");
    }

    @GetMapping(value = "/removeVehicle/{id}")
    public ModelAndView deleteVehicle(@PathVariable("id") Long id){
        vehicleService.remove(id);
        return new ModelAndView("redirect:/vehicles");
    }
}
