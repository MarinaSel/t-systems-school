package com.training.rest;

import com.training.models.Driver;
import com.training.models.User;
import com.training.services.DriverService;
import com.training.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/driver")
public class DriversResource {

    private final DriverService driverService;

    private final UserService userService;

    @Autowired
    public DriversResource(DriverService driverService, UserService userService) {
        this.driverService = driverService;
        this.userService = userService;
    }

    @GetMapping
    public List<Driver> getDrivers() {
        return driverService.findAllFree();
    }

    @GetMapping("/findByLogin/{login}")
    public User findByLogin(@PathVariable("login") String login) {
        return userService.findByLogin(login);
    }

    @GetMapping("/findByDrivingLicenseNum")
    public Driver findByDrivingLicenseNumber(@RequestParam("drivingLicenseNum") String drivingLicenseNum,
                                             @RequestParam("driverId") Long id) {
        Driver driver = driverService.findByDrivingLicenseNum(drivingLicenseNum);
        if (driver != null && driver.getId().equals(id)) {
            return null;
        }
        return driver;
    }
}
