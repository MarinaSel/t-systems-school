package com.training.models.entities;
import com.training.models.statuses.VehicleStatus;
import javax.persistence.*;
import java.util.*;

@Entity
class VehicleEntity {
    @Id
    @SequenceGenerator(name = "vehicles_id_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "vehicles_id_seq")
    private Long id;

    @Column(name = "registration_number", nullable = false)
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
    Set<LoadEntity> loads;

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private LocationEntity location;

    protected VehicleEntity(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<LoadEntity> getLoads() {
        return loads;
    }

    public void setLoads(Set<LoadEntity> loads) {
        this.loads = loads;
    }

    public LocationEntity getLocation() {
        return location;
    }

    public void setLocation(LocationEntity location) {
        this.location = location;
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
