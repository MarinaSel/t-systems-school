package com.training;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.sql.DataSource;

@Controller
public class ControllerTest {
    @RequestMapping(value = "/")
    public String home(){
        System.out.println(d.toString());
        return "test";
    }

    @Autowired
    private DataSource d;

}
