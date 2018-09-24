package com.training.models.entities;
import com.training.models.statuses.VehicleStatus;
import javax.persistence.*;
import java.util.*;

@Entity(name = "vehicle")
class Vehicle {
    @Id
    private String registrationNumber;

    @Column(name = "capacity", nullable = false)
    private Integer capacity;

    @Column(name = "status", nullable = false)
    private VehicleStatus vehicleStatus;

    @Column(name = "current_city", nullable = false)
    private String currentCity;

    @OneToMany(mappedBy = "vehicle")
    Set<DriverEntity> drivers;

    @OneToMany(mappedBy = "vehicle")
    Set<Load> loads;

    public Vehicle(String registrationNumber, Integer capacity, VehicleStatus vehicleStatus, String currentCity) {
        this.registrationNumber = registrationNumber;
        this.capacity = capacity;
        this.vehicleStatus = vehicleStatus;
        this.currentCity = currentCity;
        drivers = new HashSet<>();
        loads = new HashSet<>();
    }

    protected Vehicle(){}

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

    public VehicleStatus getVehicleStatus() {
        return vehicleStatus;
    }

    public void setVehicleStatus(VehicleStatus vehicleStatus) {
        this.vehicleStatus = vehicleStatus;
    }

    public String getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(String currentCity) {
        this.currentCity = currentCity;
    }

    public Set<DriverEntity> getDrivers() {
        return drivers;
    }

    public void setDrivers(Set<DriverEntity> drivers) {
        this.drivers = drivers;
    }

    public Set<Load> getLoads() {
        return loads;
    }

    public void setLoads(Set<Load> loads) {
        this.loads = loads;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(registrationNumber, vehicle.registrationNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(registrationNumber, capacity, vehicleStatus, currentCity, drivers, loads);
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "registrationNumber='" + registrationNumber + '\'' +
                ", capacity=" + capacity +
                ", vehicleStatus=" + vehicleStatus +
                ", currentCity='" + currentCity + '\'' +
                ", drivers=" + drivers +
                ", loads=" + loads +
                '}';
    }
}
