package com.training.controllers;

import com.training.models.Driver;
import com.training.models.Load;
import com.training.models.Vehicle;
import com.training.services.DriverService;
import com.training.services.LoadService;
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

import java.util.List;

import static org.springframework.util.StringUtils.isEmpty;

@Controller
public class LoadController {

    private final LoadService loadService;

    private final VehicleService vehicleService;

    private final DriverService driverService;

    @Autowired
    public LoadController(LoadService loadService, VehicleService vehicleService, DriverService driverService) {
        this.loadService = loadService;
        this.vehicleService = vehicleService;
        this.driverService = driverService;
    }

    @GetMapping("/editLoad/{id}")
    public ModelAndView editLoad(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        Load loadToEdit = loadService.find(id);
        List<Vehicle> vehicles = vehicleService.findAllFreeWithNecessaryCapacity(loadToEdit.getWeight());
        List<Driver> drivers = driverService.findAllFree();

        redirectAttributes.addFlashAttribute("editableLoad", loadToEdit);
        redirectAttributes.addFlashAttribute("freeVehicles", vehicles);
        redirectAttributes.addFlashAttribute("freeDrivers", drivers);
        redirectAttributes.addFlashAttribute("primaryDriverLicense");
        redirectAttributes.addFlashAttribute("coDriverLicense");
        redirectAttributes.addFlashAttribute("regNum");
        return new ModelAndView("redirect:/getSaveLoadPage");
    }

    @GetMapping("/addLoad")
    public ModelAndView addLoad(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("editableLoad", new Load());
        return new ModelAndView("redirect:/getSaveLoadPage");
    }

    @GetMapping(value = "/getSaveLoadPage")
    public ModelAndView getSaveLoadPage(Model model) {
        return new ModelAndView("saveLoadPage").addAllObjects(model.asMap());
    }

    @PostMapping(value = "/saveLoad")
    public ModelAndView saveLoad(@ModelAttribute("editableLoad") Load load,
                                 @ModelAttribute("regNum") String registrationNumber,
                                 @ModelAttribute("primaryDriverLicense") String primaryDriverLicense,
                                 @ModelAttribute("coDriverLicense") String coDriverLicense) {
        if (!isEmpty(registrationNumber)) {
            loadService.saveAssignedLoad(load, registrationNumber, primaryDriverLicense, coDriverLicense);
        } else {
            loadService.save(load);
        }
        return new ModelAndView("redirect:/loads");
    }

    @GetMapping("/loads")
    public ModelAndView getAllLoadsPage() {
        return new ModelAndView("loadsPage").addObject("loads", loadService.findAll());
    }

    @GetMapping("/delivered/{id}")
    public ModelAndView deliveredLoad(@PathVariable("id") Long id) {
        loadService.deliverLoad(id);
        return new ModelAndView("redirect:/loads");
    }

}
