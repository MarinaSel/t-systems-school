package com.training.controllers;

import com.training.entities.DriverEntity;
import com.training.services.interfaces.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
@ComponentScan("com.training.model")
public class DriverController {

    @Autowired
    private DriverService driverService;

    @GetMapping("/homePage")
    public String homePage(){
        return "homePage";
    }

    @GetMapping(value = "/addDriverPage")
    public ModelAndView getAddDriverPage() {
        return new ModelAndView("addDriverView");
    }

    @PostMapping(value="/addDriver")
    public ModelAndView addDriver(@ModelAttribute("newDriver") DriverEntity newDriver){
        driverService.create(newDriver);
        return new ModelAndView("redirect:/driversViewPage");
    }
    @GetMapping("/driversViewPage")
    public ModelAndView getAllDriversPage() {
        List<DriverEntity> drivers = driverService.getAll();
        return new ModelAndView("allDriversView", "drivers", drivers);
    }

    @GetMapping("/removingDriver/{id}")
    public ModelAndView removeDriver(@PathVariable("id") Long id){
        driverService.remove(id);
        return new ModelAndView("redirect:/driversViewPage");
    }

    @GetMapping("/driverEditingPage/{id}")
    public ModelAndView getDriverById(@PathVariable("id") Long id, RedirectAttributes redirectAttributes){
        DriverEntity driverToEdit = driverService.get(id);
        redirectAttributes.addFlashAttribute("editableDriver", driverToEdit);
        return new ModelAndView("redirect:/editingDriverView");
    }

    @GetMapping("/editingDriverView")
    public ModelAndView getEditDriverPage(Model model){
        return new ModelAndView("editDriverView", model.asMap());
    }

    @PostMapping("/updatingDriver")
    public ModelAndView editDriverView(@ModelAttribute("editableDriver") DriverEntity driver){
        driverService.update(driver);
        return new ModelAndView("redirect:/driversViewPage");
    }
}

