package com.training.controllers;

import com.training.entities.enums.VehicleStatus;
import com.training.models.Vehicle;
import com.training.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping("/vehicles")
    public ModelAndView getAllVehiclesView() {
        return new ModelAndView("vehiclesView").addObject("vehicles", vehicleService.getAll());
    }

    @GetMapping("/editVehicle/{id}")
    public ModelAndView getVehicleById(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("statuses", VehicleStatus.values());
        redirectAttributes.addFlashAttribute("editableVehicle", vehicleService.get(id));
        return new ModelAndView("redirect:/getSaveVehiclePage");
    }

    @GetMapping("/addVehicle")
    public ModelAndView addVehicle(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("editableVehicle", new Vehicle());
        return new ModelAndView("redirect:/getSaveVehiclePage");
    }

    @GetMapping("/getSaveVehiclePage")
    public ModelAndView getSaveVehiclePage(Model model) {
        return new ModelAndView("saveVehicleView").addAllObjects(model.asMap());
    }

    @PostMapping("/saveVehicle")
    public ModelAndView saveVehicle(@ModelAttribute("editableVehicle") Vehicle vehicle) {
        vehicleService.save(vehicle);
        return new ModelAndView("redirect:/vehicles");
    }

    @GetMapping("/removeVehicle/{id}")
    public ModelAndView removeVehicle(@PathVariable("id") Long id) {
        vehicleService.remove(id);
        return new ModelAndView("redirect:/vehicles");
    }

    @GetMapping("/sent/{id}")
    public ModelAndView beginDelivery(@PathVariable("id") Long id) {
        vehicleService.changeStatusWhenStartingDelivery(id);
        return new ModelAndView("redirect:/loads");
    }
}
