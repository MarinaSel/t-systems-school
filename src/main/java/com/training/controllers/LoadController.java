package com.training.controllers;

import com.training.models.Driver;
import com.training.models.Load;
import com.training.services.DriverService;
import com.training.services.LoadService;
import com.training.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static org.springframework.util.StringUtils.isEmpty;

@Controller
@RequestMapping("/load")
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
    public ModelAndView editLoad(@PathVariable("id") Long id, ModelAndView modelAndView) {
        Load loadToEdit = loadService.find(id);
        List<Driver> drivers = driverService.findAllFree();

        modelAndView.addObject("editableLoad", loadToEdit);
        modelAndView.addObject("freeDrivers", drivers);
        modelAndView.addObject("primaryDriverLicense");
        modelAndView.addObject("coDriverLicense");
        modelAndView.addObject("regNum");
        modelAndView.setViewName("saveLoadPage");
        return modelAndView;
    }

    @GetMapping("/addLoad")
    public ModelAndView addLoad(ModelAndView modelAndView) {
        modelAndView.addObject("editableLoad", new Load()).setViewName("saveLoadPage");
        return modelAndView;
    }

    @PostMapping("/saveLoad")
    public ModelAndView saveLoad(@ModelAttribute("editableLoad") Load load,
                                 @ModelAttribute("regNum") String registrationNumber,
                                 @ModelAttribute("primaryDriverLicense") String primaryDriverLicense,
                                 @ModelAttribute("coDriverLicense") String coDriverLicense) {
        if (!isEmpty(registrationNumber)) {
            loadService.saveAssignedLoad(load, registrationNumber, primaryDriverLicense, coDriverLicense);
        } else {
            loadService.save(load);
        }
        return new ModelAndView("redirect:/load/allLoads");
    }

    @GetMapping("/allLoads")
    public ModelAndView getAllLoadsPage() {
        return new ModelAndView("loadsPage").addObject("loads", loadService.findAll());
    }
}
