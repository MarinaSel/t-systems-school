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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public ModelAndView editVehicle(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("statuses", VehicleStatus.values());
        redirectAttributes.addFlashAttribute("editableVehicle", vehicleService.find(id));
        return new ModelAndView("redirect:/vehicle/getSaveVehiclePage");
    }

    @GetMapping("/addVehicle")
    public ModelAndView addVehicle(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("editableVehicle", new Vehicle());
        return new ModelAndView("redirect:/vehicle/getSaveVehiclePage");
    }

    @GetMapping("/getSaveVehiclePage")
    public ModelAndView getSaveVehiclePage(Model model) {
        return new ModelAndView("saveVehiclePage").addAllObjects(model.asMap());
    }

    @PostMapping("/saveVehicle")
    public ModelAndView saveVehicle(@ModelAttribute("editableVehicle") Vehicle vehicle) {
        vehicleService.save(vehicle);
        return new ModelAndView("redirect:/vehicle/allVehicles");
    }
}
