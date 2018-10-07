package com.training.controllers;

import com.training.entities.LoadEntity;
import com.training.models.Load;
import com.training.services.interfaces.LoadService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class LoadController {

    @Autowired
    private LoadService loadService;

    @GetMapping(value = "/load")
    public ModelAndView addLoadView() {
        return new ModelAndView("addLoad", "newLoad", new Load());
    }

    @PostMapping(value="/addLoad")
    public ModelAndView addLoad(@ModelAttribute("newLoad") Load newLoad){
        loadService.create(newLoad);
        return new ModelAndView("redirect:/loadsView");
    }
    @RequestMapping("/loadsView")
    public ModelAndView viewAllLoads() {
        List<Load> loads = loadService.getAll();
        return new ModelAndView("loadsView", "loads", loads);
    }

}
