package com.training.controllers;

import com.training.entities.enums.VehicleStatus;
import com.training.models.Vehicle;
import com.training.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/vehicle")
public class VehicleController {

    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping("/allVehicles")
    public ModelAndView getAllVehiclesPage() {
        return new ModelAndView("vehiclesPage").addObject("vehicles", vehicleService.findAll());
    }

    @GetMapping("/editVehicle/{id}")
    public ModelAndView editVehicle(@PathVariable("id") Long id, ModelAndView modelAndView) {
        modelAndView.addObject("statuses", VehicleStatus.values());
        modelAndView.addObject("editableVehicle", vehicleService.find(id));
        modelAndView.setViewName("saveVehiclePage");
        return modelAndView;
    }

    @GetMapping("/addVehicle")
    public ModelAndView addVehicle(ModelAndView modelAndView) {
        modelAndView.addObject("editableVehicle", new Vehicle()).setViewName("saveVehiclePage");
        return modelAndView;
    }

    @PostMapping("/saveVehicle")
    public ModelAndView saveVehicle(@ModelAttribute("editableVehicle") Vehicle vehicle) {
        vehicleService.save(vehicle);
        return new ModelAndView("redirect:/vehicle/allVehicles");
    }

    @GetMapping("/repairedVehicle/{id}")
    public ModelAndView repairedVehicle(@PathVariable("id") Long id) {
        vehicleService.repaired(id);
        return new ModelAndView("redirect:/vehicle/allVehicles");
    }

    @GetMapping("/brokenVehicle/{id}")
    public ModelAndView brokenVehicle(@PathVariable("id") Long id) {
        vehicleService.setBroken(id);
        return new ModelAndView("redirect:/vehicle/allVehicles");
    }
}
