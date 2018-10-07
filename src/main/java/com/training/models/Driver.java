package com.training.models;

import com.training.entities.enums.DriverStatus;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Driver {

    private Long id;
    private String firstName;
    private String lastName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date licenseEndDate;
    private String drivingLicenseNum;
    private DriverStatus status;

    public Driver(){}

    public Driver(Long id, String firstName, String lastName, Date licenseEndDate, String drivingLicenseNum, DriverStatus status) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.licenseEndDate = licenseEndDate;
        this.drivingLicenseNum = drivingLicenseNum;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
