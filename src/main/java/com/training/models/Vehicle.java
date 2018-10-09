package com.training.models;

import com.training.entities.enums.VehicleStatus;
import liquibase.exception.DatabaseException;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Set;

public class Vehicle extends BaseModel{

    private Long id;
    private String registrationNumber;
    private Integer capacity;
    private VehicleStatus status;
    private Set<Driver> drivers;
    private Set<Load> loads;

    public Vehicle(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(Set<Driver> drivers) {
        this.drivers = drivers;
    }

    public Set<Load> getLoads() {
        return loads;
    }

    public void setLoads(Set<Load> loads) {
        this.loads = loads;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public VehicleStatus getStatus() {
        return status;
    }

    public void setStatus(VehicleStatus loadStatus) {
        this.status = loadStatus;
    }
}
