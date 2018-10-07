package com.training.controllers;

import com.training.entities.LoadEntity;
import com.training.services.interfaces.LoadService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping
public class LoadController {

    @Autowired
    LoadService loadService;

    @RequestMapping(value = "/load", method = RequestMethod.GET)
    public ModelAndView addLoadView() {
        return new ModelAndView("addLoad", "newLoad", new LoadEntity());
    }

    @RequestMapping(value="/addLoad",method = RequestMethod.POST)
    public ModelAndView addLoad(@ModelAttribute("newLoad") LoadEntity newLoad){
        loadService.create(newLoad);
        return new ModelAndView("redirect:/loadsView");
    }
    @RequestMapping("/loadsView")
    public ModelAndView viewAllLoads() {
        List<LoadEntity> loads = loadService.getAll();
        return new ModelAndView("loadsView", "loads", loads);
    }

}
