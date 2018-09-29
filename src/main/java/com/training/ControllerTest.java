package com.training;

import com.training.services.impl.DriverServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@EnableJpaRepositories("com.training.repositories")
public class ControllerTest {
    @Autowired
    public ControllerTest(DriverServiceImpl driverService) {
        this.driverService = driverService;
    }

    @RequestMapping(value = "/")
    public String home(){
        return "test";
    }

    private final DriverServiceImpl driverService;

}
