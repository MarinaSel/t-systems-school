package com.training.models;

import com.training.entities.enums.DriverStatus;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Driver extends BaseModel{

    private Long id;
    private String firstName;
    private String lastName;
    private String drivingLicenseNum;
    private DriverStatus status;
    private Vehicle vehicle;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date licenseEndDate;

    public Driver(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public String getDrivingLicenseNum() {
        return drivingLicenseNum;
    }

    public void setDrivingLicenseNum(String drivingLicenseNum) {
        this.drivingLicenseNum = drivingLicenseNum;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getLicenseEndDate() {
        return licenseEndDate;
    }

    public void setLicenseEndDate(Date licenseEndDate) {
        this.licenseEndDate = licenseEndDate;
    }

    public DriverStatus getStatus() {
        return status;
    }

    public void setStatus(DriverStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", drivingLicenseNum='" + drivingLicenseNum + '\'' +
                ", status=" + status +
                ", vehicle=" + vehicle +
                ", licenseEndDate=" + licenseEndDate +
                '}';
    }
}
