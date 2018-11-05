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

    @Autowired
    private LoadService loadService;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private DriverService driverService;

    @GetMapping("/editLoad/{id}")
    public ModelAndView editLoad(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        Load loadToEdit = loadService.get(id);
        List<Vehicle> vehicles = vehicleService.getAllFreeWithNecessaryCapacity(loadToEdit.getWeight());
        List<Driver> drivers = driverService.getAllFree();

        redirectAttributes.addFlashAttribute("editableLoad", loadToEdit);
        redirectAttributes.addFlashAttribute("freeVehicles", vehicles);
        redirectAttributes.addFlashAttribute("freeDrivers", drivers);
        redirectAttributes.addFlashAttribute("drivingLicenseNumPrimary", "");
        redirectAttributes.addFlashAttribute("drivingLicenseNumSecond", "");
        redirectAttributes.addFlashAttribute("regNum", "");
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

    //TODO move business logic to service
    @PostMapping(value = "/saveLoad")
    public ModelAndView saveLoad(@ModelAttribute("editableLoad") Load load,
                                 @ModelAttribute("regNum") String registrationNumber,
                                 @ModelAttribute("drivingLicenseNumPrimary") String drivingLicenseNumPrimary,
                                 @ModelAttribute("drivingLicenseNumSecond") String drivingLicenseNumSecond) {

        if (!isEmpty(registrationNumber)) {
            Vehicle vehicle = vehicleService.findByRegistrationNumber(registrationNumber);
            load.setVehicle(vehicle);

            if (!isEmpty(drivingLicenseNumPrimary)) {
                Driver primaryDriver = driverService.findByDrivingLicenseNum(drivingLicenseNumPrimary);
                vehicle.setPrimaryDriver(primaryDriver);
                vehicleService.save(vehicle);
                driverService.save(primaryDriver);
            }
            if (!isEmpty(drivingLicenseNumSecond)) {
                Driver coDriver = driverService.findByDrivingLicenseNum(drivingLicenseNumSecond);
                vehicle.setCoDriver(coDriver);
                vehicleService.save(vehicle);
                driverService.save(coDriver);
            }
        }
        loadService.save(load);
        return new ModelAndView("redirect:/loads");
    }

    @GetMapping("/loads")
    public ModelAndView getAllLoadsPage() {
        return new ModelAndView("loadsPage").addObject("loads", loadService.getAll());
    }

    @GetMapping("/delivered/{id}")
    public ModelAndView deliveredLoad(@PathVariable("id") Long id) {
        loadService.deleteVehicleFromLoad(id);
        return new ModelAndView("redirect:/loads");
    }

}
