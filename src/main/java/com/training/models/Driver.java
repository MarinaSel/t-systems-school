package com.training.models;

import com.training.entities.enums.DriverStatus;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.Date;

public class Driver extends BaseModel{

    private Long id;

    @NotNull(message = "First name cannot be null")
    @Size(max = 50, message = "First name is invalid")
    private String firstName;

    @NotNull(message = "Last name cannot be null")
    @Size(max = 50,  message = "Last name is invalid")
    private String lastName;

    @NotNull(message = "Driving license number cannot be null")
    @Size(max = 50, message = "Driving license number is invalid")
    private String drivingLicenseNum;
    private DriverStatus status;
    private Vehicle vehicle;

    @NotNull(message = "License end date cannot be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date licenseEndDate;

    public Driver(){}

    public Driver(Long id, @NotNull(message = "First name cannot be null")
                  @Size(max = 50, message = "First name is invalid") String firstName,
                  @NotNull(message = "Last name cannot be null") @Size(max = 50, message = "Last name is invalid") String lastName,
                  @NotNull(message = "Driving license number cannot be null")
                  @Size(max = 50, message = "Driving license number is invalid") String drivingLicenseNum,
                  DriverStatus status, Vehicle vehicle, @NotNull(message = "License end date cannot be null") Date licenseEndDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.drivingLicenseNum = drivingLicenseNum;
        this.status = status;
        this.vehicle = vehicle;
        this.licenseEndDate = licenseEndDate;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Driver driver = (Driver) o;

        if (id != null ? !id.equals(driver.id) : driver.id != null) return false;
        if (firstName != null ? !firstName.equals(driver.firstName) : driver.firstName != null) return false;
        if (lastName != null ? !lastName.equals(driver.lastName) : driver.lastName != null) return false;
        if (drivingLicenseNum != null ? !drivingLicenseNum.equals(driver.drivingLicenseNum) : driver.drivingLicenseNum != null)
            return false;
        if (status != driver.status) return false;
        if (vehicle != null ? !vehicle.equals(driver.vehicle) : driver.vehicle != null) return false;
        return licenseEndDate != null ? licenseEndDate.equals(driver.licenseEndDate) : driver.licenseEndDate == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (drivingLicenseNum != null ? drivingLicenseNum.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (vehicle != null ? vehicle.hashCode() : 0);
        result = 31 * result + (licenseEndDate != null ? licenseEndDate.hashCode() : 0);
        return result;
    }
}
