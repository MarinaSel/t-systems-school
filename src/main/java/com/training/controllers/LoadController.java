package com.training.controllers;

import com.training.entities.enums.LoadStatus;
import com.training.entities.enums.VehicleStatus;
import com.training.models.Load;
import com.training.models.Vehicle;
import com.training.services.interfaces.DriverService;
import com.training.services.interfaces.LoadService;

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
public class LoadController {

    @Autowired
    private LoadService loadService;

    @Autowired
    private VehicleService vehicleService;

    @GetMapping("/editLoad/{id}")
    public ModelAndView getLoadById(@PathVariable("id") Long id, RedirectAttributes redirectAttributes){
        Load loadToEdit = loadService.get(id);
        String registrationNumber = "";
        List<Vehicle> vehicles = vehicleService.getAllFreeWithNecessaryCapacityAndDrivers(loadToEdit.getWeight());

        redirectAttributes.addFlashAttribute("editableLoad", loadToEdit);
        redirectAttributes.addFlashAttribute("freeVehicles", vehicles);
        redirectAttributes.addFlashAttribute("regNum", registrationNumber);

        return new ModelAndView("redirect:/getSaveLoadPage");
    }

    @GetMapping("/addLoad")
    public ModelAndView addLoad(RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("editableLoad", new Load());
        return new ModelAndView("redirect:/getSaveLoadPage");
    }

    @GetMapping(value = "/getSaveLoadPage")
    public ModelAndView getSaveLoadView(Model model) {
        return new ModelAndView("saveLoadView", model.asMap());
    }

    @PostMapping(value="/saveLoad")
    public ModelAndView saveLoad(@ModelAttribute("editableLoad") Load load,
                                 @ModelAttribute("regNum") String registrationNumber){
        Vehicle vehicle = vehicleService.findByRegistrationNumber(registrationNumber);
        if (registrationNumber != null && registrationNumber.length() != 0){
            load.setVehicle(vehicle);
            vehicle.setStatus(VehicleStatus.WORKING);
            load.setStatus(LoadStatus.IN_PROGRESS);
        }
        loadService.create(load);
        return new ModelAndView("redirect:/loads");
    }

    @GetMapping("/loads")
    public ModelAndView viewAllLoads() {
        List<Load> loads = loadService.getAll();
        return new ModelAndView("loadsView", "loads", loads);
    }



}
