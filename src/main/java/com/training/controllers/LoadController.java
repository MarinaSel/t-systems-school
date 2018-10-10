package com.training.controllers;

import com.training.models.Load;
import com.training.models.Vehicle;
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
        List<Vehicle> vehicles = vehicleService.getAllFreeWithNecessaryCapacity(loadToEdit.getWeight());
        redirectAttributes.addFlashAttribute("editableLoad", loadToEdit);
        redirectAttributes.addFlashAttribute("freeVehicles", vehicles);

        return new ModelAndView("redirect:/getAddLoadPage");
    }

    @GetMapping(value = "/getAddLoadPage")
    public ModelAndView getAddLoadView(Model model) {
        return new ModelAndView("addLoadView", model.asMap());
    }

    @PostMapping(value="/addLoad")
    public ModelAndView addLoad(@ModelAttribute("editableLoad") Load newLoad, Model model){
        loadService.create(newLoad);
        return new ModelAndView("redirect:/loads");
    }

    @GetMapping("/loads")
    public ModelAndView viewAllLoads() {
        List<Load> loads = loadService.getAll();
        return new ModelAndView("loadsView", "loads", loads);
    }
}
