package com.training.controllers;

import com.training.entities.DriverEntity;
import com.training.services.interfaces.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@RequestMapping
public class DriverController {

    @Autowired
    private DriverService driverService;

    @RequestMapping("/homePage")
    public String homePage(){
        return "homePage";
    }

    @RequestMapping(value = "/driver", method = RequestMethod.GET)
    public ModelAndView addDriverView() {
        return new ModelAndView("addDriver", "newDriver", new DriverEntity());
    }

    @RequestMapping(value="/addDriver",method = RequestMethod.POST)
    public ModelAndView addDriver(@ModelAttribute("newDriver") DriverEntity newDriver){
        driverService.create(newDriver);
        return new ModelAndView("redirect:/driversView");
    }
    @RequestMapping("/driversView")
    public ModelAndView viewAllDrivers() {
        List<DriverEntity> drivers = driverService.getAll();
        return new ModelAndView("driversView", "drivers", drivers);
    }

    @RequestMapping("/removeDriver/{id}")
    public ModelAndView deleteDriver(@PathVariable("id") Long id){
        driverService.remove(id);
        return new ModelAndView("redirect:/driversView");
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView editDriver(@PathVariable("id") Long id){
        DriverEntity driver = driverService.get(id);
        return new ModelAndView("redirect:/editDriver","editedDriver", driver);
    }

    @RequestMapping("/editDriver")
    public ModelAndView editDriverView(){
        return new ModelAndView("editDriver");
    }

    @RequestMapping(value="/editSave",method = RequestMethod.POST)
    public ModelAndView editSave(@ModelAttribute("editedDriver") DriverEntity driver ){
        System.out.println(driver.toString());
        driverService.update(driver);
        return new ModelAndView("redirect:/driversView");
    }

}

