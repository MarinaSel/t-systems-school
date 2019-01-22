package com.training.controllers;

import com.training.models.Driver;
import com.training.models.Load;
import com.training.models.Location;
import com.training.services.DriverService;
import com.training.services.LoadService;
import com.training.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/load")
public class LoadController {

    private final LoadService loadService;
    private final DriverService driverService;
    private final LocationService locationService;

    @Autowired
    public LoadController(LoadService loadService, DriverService driverService, LocationService locationService) {
        this.loadService = loadService;
        this.driverService = driverService;
        this.locationService = locationService;
    }

    @GetMapping("/editLoad/{id}")
    public ModelAndView editLoad(@PathVariable("id") Long id, ModelAndView modelAndView) {
        Load loadToEdit = loadService.find(id);
        List<Driver> drivers = driverService.findAllFree();
        List<Location> locations = locationService.findAll();

        modelAndView.addObject("editableLoad", loadToEdit);
        modelAndView.addObject("freeDrivers", drivers);
        modelAndView.addObject("locations", locations);
        modelAndView.addObject("primaryDriverId");
        modelAndView.addObject("coDriverId");
        modelAndView.addObject("vehicleId");
        modelAndView.setViewName("saveLoadPage");
        return modelAndView;
    }

    @GetMapping("/addLoad")
    public ModelAndView addLoad(ModelAndView modelAndView) {
        List<Location> locations = locationService.findAll();
        modelAndView.addObject("locations", locations);
        modelAndView.addObject("editableLoad", new Load()).setViewName("saveLoadPage");
        return modelAndView;
    }

    @PostMapping("/saveLoad")
    public ModelAndView saveLoad(@ModelAttribute("editableLoad") Load load,
                                 @ModelAttribute("regNum") String registrationNumber,
                                 @ModelAttribute("primaryDriverLicense") String primaryDriverLicense,
                                 @ModelAttribute("coDriverLicense") String coDriverLicense,
                                 @RequestParam("pickUpLocationId") Long pickUpLocationId,
                                 @RequestParam("deliveryLocationId") Long deliveryLocationId) {
        loadService.saveAssignedLoad(load, registrationNumber, primaryDriverLicense, coDriverLicense,
                pickUpLocationId, deliveryLocationId);

        return new ModelAndView("redirect:/load/allLoads");
    }

    @GetMapping("/allLoads")
    public ModelAndView getAllLoadsPage() {
        return new ModelAndView("loadsPage").addObject("loads", loadService.findAll());
    }

    @GetMapping("/doneLoads")
    public ModelAndView getDoneLoadsPage() {
        return new ModelAndView("doneLoadsPage").addObject("doneLoads", loadService.findDoneLoads());
    }
}
