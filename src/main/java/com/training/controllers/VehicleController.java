package com.training.controllers;

import com.training.models.Driver;
import com.training.models.Vehicle;
import com.training.services.interfaces.DriverService;
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

    @Autowired
    private DriverService driverService;

    @GetMapping("/vehicles")
    public ModelAndView allVehiclesView() {
        List<Vehicle> vehicles = vehicleService.getAll();
        return new ModelAndView("vehiclesView", "vehicles", vehicles);
    }

    @GetMapping("/editVehicle/{id}")
    public ModelAndView getVehicleById(@PathVariable("id") Long id, RedirectAttributes redirectAttributes){
        Vehicle vehicleToEdit = vehicleService.get(id);
        redirectAttributes.addFlashAttribute("editableVehicle", vehicleToEdit);
        return new ModelAndView("redirect:/getSaveVehiclePage");
    }

    @GetMapping("/addVehicle")
    public ModelAndView addVehicle(RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("editableVehicle", new Vehicle());
        return new ModelAndView("redirect:/getSaveVehiclePage");
    }

    @GetMapping(value = "/getSaveVehiclePage")
    public ModelAndView getSaveVehiclePage(Model model) {
        return new ModelAndView("saveVehicleView", model.asMap());
    }

    @PostMapping(value="/saveVehicle")
    public ModelAndView saveVehicle(@ModelAttribute("editableVehicle") Vehicle vehicle){

        vehicleService.create(vehicle);
        return new ModelAndView("redirect:/vehicles");
    }

    @GetMapping(value = "/removeVehicle/{id}")
    public ModelAndView deleteVehicle(@PathVariable("id") Long id){
        vehicleService.remove(id);
        return new ModelAndView("redirect:/vehicles");
    }
}
