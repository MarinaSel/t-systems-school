package com.training.controllers;

import com.training.entities.enums.DriverStatus;
import com.training.models.Driver;
import com.training.services.DriverService;
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
public class DriverController {

    @Autowired
    private DriverService driverService;

    @GetMapping("/editDriver/{id}")
    public ModelAndView editDriver(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        Driver driverToEdit = driverService.find(id);
        redirectAttributes.addFlashAttribute("statuses", DriverStatus.values());
        redirectAttributes.addFlashAttribute("editableDriver", driverToEdit);
        return new ModelAndView("redirect:/getSaveDriverPage");
    }

    @GetMapping("/addDriver")
    public ModelAndView getAddDriverPage(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("editableDriver", new Driver());
        return new ModelAndView("redirect:/getSaveDriverPage");
    }

    @GetMapping(value = "/getSaveDriverPage")
    public ModelAndView getSaveDriverPage(Model model) {
        return new ModelAndView("saveDriverPage").addAllObjects(model.asMap());
    }

    @PostMapping("/saveDriver")
    public ModelAndView saveDriver(@ModelAttribute("editableDriver") Driver driver) {
        driverService.save(driver);
        return new ModelAndView("redirect:/drivers");
    }

    @GetMapping("/drivers")
    public ModelAndView getAllDriversPage() {
        List<Driver> drivers = driverService.findAll();
        return new ModelAndView("driversPage").addObject("drivers", drivers);
    }

    @GetMapping("/fireDriver/{id}")
    public ModelAndView fireDriver(@PathVariable("id") Long id) {
        driverService.fireDriver(id);
        return new ModelAndView("redirect:/drivers");
    }
}

