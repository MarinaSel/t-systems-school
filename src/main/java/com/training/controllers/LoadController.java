package com.training.controllers;

import com.training.entities.enums.LoadStatus;
import com.training.models.Driver;
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

    @Autowired
    private DriverService driverService;

    @GetMapping("/editLoad/{id}")
    public ModelAndView getLoadById(@PathVariable("id") Long id, RedirectAttributes redirectAttributes){
        Load loadToEdit = loadService.get(id);
        String registrationNumber = "";
        List<Vehicle> vehicles = vehicleService.getAllFreeWithNecessaryCapacityAndDrivers(loadToEdit.getWeight());
        List<Driver> drivers = driverService.getAllFree();

        String namePrimary = "";
        String nameSecond = "";

        String drivingLicenseNumPrimary = "";
        String drivingLicenseNumSecond = "";

        redirectAttributes.addFlashAttribute("editableLoad", loadToEdit);
        redirectAttributes.addFlashAttribute("freeVehicles", vehicles);
        redirectAttributes.addFlashAttribute("freeDrivers", drivers);
        redirectAttributes.addFlashAttribute("namePrimary", namePrimary);
        redirectAttributes.addFlashAttribute("nameSecond", nameSecond);
        redirectAttributes.addFlashAttribute("drivingLicenseNumPrimary", drivingLicenseNumPrimary);
        redirectAttributes.addFlashAttribute("drivingLicenseNumSecond", drivingLicenseNumSecond);

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
        return new ModelAndView("saveLoadView").addAllObjects(model.asMap());
    }

    @PostMapping(value="/saveLoad")
    public ModelAndView saveLoad(@ModelAttribute("editableLoad") Load load,
                                 @ModelAttribute("regNum") String registrationNumber,
                                 @ModelAttribute("namePrimary") String namePrimary,
                                 @ModelAttribute("nameSecond") String nameSecond,
                                 @ModelAttribute("drivingLicenseNumPrimary") String drivingLicenseNumPrimary,
                                 @ModelAttribute("drivingLicenseNumSecond") String drivingLicenseNumSecond){

        if (registrationNumber != null && registrationNumber.length() != 0){
            Vehicle vehicle = vehicleService.findByRegistrationNumber(registrationNumber);
            load.setVehicle(vehicle);

            if(drivingLicenseNumPrimary != null && drivingLicenseNumPrimary.length() > 0){
                Driver primaryDriver = driverService.findByDrivingLicenseNum(drivingLicenseNumPrimary);
                primaryDriver.setVehicle(vehicle);
                driverService.save(primaryDriver);
            }
            if (drivingLicenseNumSecond != null && drivingLicenseNumSecond.length() > 0){
                Driver secondDriver = driverService.findByDrivingLicenseNum(drivingLicenseNumSecond);
                secondDriver.setVehicle(vehicle);
                driverService.save(secondDriver);
            }
        }
        loadService.save(load);
        return new ModelAndView("redirect:/loads");
    }

    @GetMapping("/loads")
    public ModelAndView viewAllLoads() {
        List<Load> loads = loadService.getAll();
        return new ModelAndView("loadsView").addObject("loads", loads);
    }

    @GetMapping("/loadDone/{id}")
    public ModelAndView deliveredLoad(@PathVariable("id") Long id){
        Load load = loadService.deleteVehicleFromLoad(id);
        loadService.save(load);
        return new ModelAndView("redirect:/loads");
    }

}
