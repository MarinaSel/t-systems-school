package com.training;

import com.training.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@EnableJpaRepositories("com.training.repository")
public class ControllerTest {
    @Autowired
    public ControllerTest(DriverService driverService) {
        this.driverService = driverService;
    }

    @RequestMapping(value = "/")
    public String home(){
        return "test";
    }

    private final DriverService driverService;

}
