package com.training.controllers;

import com.training.entities.enums.DriverStatus;
import com.training.models.Driver;
import com.training.models.User;
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
        Driver driverToEdit = driverService.get(id);
        redirectAttributes.addFlashAttribute("editableUser", driverToEdit.getUser());
        redirectAttributes.addFlashAttribute("statuses", DriverStatus.values());
        redirectAttributes.addFlashAttribute("editableDriver", driverToEdit);
        return new ModelAndView("redirect:/getSaveDriverView");
    }

    @GetMapping("/addDriver")
    public ModelAndView getAddDriverPage(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("editableUser", new User());
        redirectAttributes.addFlashAttribute("editableDriver", new Driver());
        return new ModelAndView("redirect:/getSaveDriverView");
    }

    @GetMapping(value = "/getSaveDriverView")
    public ModelAndView getSaveDriverView(Model model) {
        return new ModelAndView("saveDriverView").addAllObjects(model.asMap());
    }

    @PostMapping("/saveDriver")
    public ModelAndView saveDriver(@ModelAttribute("editableDriver") Driver driver,
                                   @ModelAttribute("editableUser") User user) {
        driver.setUser(user);
        driverService.save(driver);
        return new ModelAndView("redirect:/drivers");
    }

    @GetMapping("/drivers")
    public ModelAndView getAllDriversPage() {
        List<Driver> drivers = driverService.getAll();
        return new ModelAndView("driversView").addObject("drivers", drivers);
    }

    @GetMapping("/removeDriver/{id}")
    public ModelAndView removeDriver(@PathVariable("id") Long id) {
        driverService.remove(id);
        return new ModelAndView("redirect:/drivers");
    }
}

