package com.training.entities;
import com.training.entities.enums.VehicleStatus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "vehicles")
public class VehicleEntity extends BaseEntity {
    @Id
    @SequenceGenerator(name = "vehicles_id_seq", initialValue = 1, sequenceName = "vehicles_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vehicles_id_seq")
    private Long id;

    @Size(max = 7)
    @NotNull
    @Column(name = "registration_number", nullable = false, length = 7, unique = true)
    private String registrationNumber;

    @NotNull
    @Column(name = "capacity", nullable = false)
    private Integer capacity;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private VehicleStatus status;

    @NotNull
    @Column(name = "current_city")
    private String currentCity;

    @OneToMany(mappedBy = "vehicle")
    private Set<DriverEntity> drivers;

    @OneToMany(mappedBy = "vehicle")
    private Set<LoadEntity> loads;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private LocationEntity location;

    @PrePersist
    public void prePersist() {
        if(status == null)
            status = VehicleStatus.UNWORKING;
    }

    public VehicleEntity(){}

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

    public VehicleStatus getStatus() {
        return status;
    }

    public void setStatus(VehicleStatus status) {
        this.status = status;
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
                ", status=" + status +
                ", currentCity='" + currentCity + '\'' +
                ", drivers=" + drivers +
                ", loads=" + loads +
                '}';
    }
}
