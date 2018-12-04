package com.training.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @RequestMapping(value = "/loginPage", method = RequestMethod.GET)
    public ModelAndView loginForm(@RequestParam(value = "error", required = false) String error) {
        ModelAndView model = new ModelAndView();

        if (error != null) {
            model.addObject("error", "Invalid username and password!");
        }
        model.setViewName("loginPage");
        return model;
    }

    @GetMapping("/accessDenied")
    public ModelAndView accessDenied() {
        return new ModelAndView("accessDeniedPage");
    }
}
