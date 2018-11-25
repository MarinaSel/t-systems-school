package com.training.models;

import com.training.entities.enums.VehicleStatus;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Vehicle extends BaseModel {

    private Long id;
    private String model;
    private Driver primaryDriver;
    private Driver coDriver;
    private Set<Load> loads = new HashSet<>();

    @NotNull(message = "Capacity cannot be null")
    private Integer capacity;

    @NotNull(message = "Registration number cannot be null")
    @Size(min = 7, max = 7, message = "Registration number is invalid")
    private String registrationNumber;

    @NotNull(message = "Status cannot be null")
    private VehicleStatus status;

    @NotNull(message = "Issue date cannot be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date issueDate;

    public Vehicle(Long id, String registrationNumber, Integer capacity, VehicleStatus status, String model, Date date) {
        this.id = id;
        this.registrationNumber = registrationNumber;
        this.capacity = capacity;
        this.status = status;
        this.model = model;
        this.issueDate = date;
    }

    public Vehicle() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Driver getPrimaryDriver() {
        return primaryDriver;
    }

    public void setPrimaryDriver(Driver primaryDriver) {
        this.primaryDriver = primaryDriver;
    }

    public Driver getCoDriver() {
        return coDriver;
    }

    public void setCoDriver(Driver coDriver) {
        this.coDriver = coDriver;
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

    public void setStatus(VehicleStatus status) {
        this.status = status;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vehicle vehicle = (Vehicle) o;

        if (id != null ? !id.equals(vehicle.id) : vehicle.id != null) return false;
        if (registrationNumber != null ? !registrationNumber.equals(vehicle.registrationNumber) :
                vehicle.registrationNumber != null)
            return false;
        if (capacity != null ? !capacity.equals(vehicle.capacity) : vehicle.capacity != null) return false;
        if (status != vehicle.status) return false;
        if (model != null ? !model.equals(vehicle.model) : vehicle.model != null) return false;
        if (issueDate != null ? !issueDate.equals(vehicle.issueDate) : vehicle.issueDate != null) return false;
        if (primaryDriver != null ? !primaryDriver.equals(vehicle.primaryDriver) : vehicle.primaryDriver != null)
            return false;
        if (coDriver != null ? !coDriver.equals(vehicle.coDriver) : vehicle.coDriver != null) return false;
        return loads != null ? loads.equals(vehicle.loads) : vehicle.loads == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (registrationNumber != null ? registrationNumber.hashCode() : 0);
        result = 31 * result + (capacity != null ? capacity.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (issueDate != null ? issueDate.hashCode() : 0);
        result = 31 * result + (primaryDriver != null ? primaryDriver.hashCode() : 0);
        result = 31 * result + (coDriver != null ? coDriver.hashCode() : 0);
        result = 31 * result + (loads != null ? loads.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", capacity=" + capacity +
                ", status=" + status +
                ", model='" + model + '\'' +
                ", issueDate=" + issueDate +
                ", primaryDriver=" + primaryDriver +
                ", coDriver=" + coDriver +
                ", loads=" + loads +
                '}';
    }
}
