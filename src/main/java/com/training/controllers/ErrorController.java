package com.training.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorController {

    @GetMapping("/error")
    public ModelAndView errorPage(HttpServletRequest httpRequest) {

        ModelAndView model = new ModelAndView("error");
        int httpErrorCode = 404;
        return model;
    }

}
