package com.training.controllers;

import com.training.models.Driver;
import com.training.services.interfaces.DriverService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
public class DriverController {

    @Autowired
    private DriverService driverService;

    @GetMapping("/editDriver/{id}")
    public ModelAndView getDriverById(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        Driver driverToEdit = driverService.get(id);
        redirectAttributes.addFlashAttribute("editableDriver", driverToEdit);
        return new ModelAndView("redirect:/getAddDriverView");
    }

    @GetMapping("/getAddDriverView")
    public ModelAndView getAddDriverPage(Model model) {
        return new ModelAndView("addDriverView").addAllObjects(model.asMap());
    }

    @PostMapping("/addDriver")
    public ModelAndView addDriver(@ModelAttribute("editableDriver") Driver driver) {
        driverService.save(driver);
        return new ModelAndView("redirect:/drivers");
    }

    @GetMapping("/drivers")
    public ModelAndView getAllDriversPage() {
        List<Driver> drivers = driverService.getAll();
        return new ModelAndView("driversView").addObject("drivers", drivers);
    }

    @GetMapping("/removeDriver/{id}")
    public ModelAndView removeDriver(@PathVariable("id") Long id){
        driverService.remove(id);
        return new ModelAndView("redirect:/drivers");
    }
}

