package com.training.models;

import com.training.entities.enums.DriverStatus;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class Driver extends BaseModel {

    @NotNull(message = "Driving license number cannot be null")
    @Size(max = 50, message = "Driving license number is invalid")
    private String drivingLicenseNum;

    @NotNull(message = "License end date cannot be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date licenseEndDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfFire;

    private Long id;
    private DriverStatus status;
    private User user;

    public Driver() {
        this.user = new User();
    }

    public Driver(Long id, String drivingLicenseNum, DriverStatus status, Date licenseEndDate,
                  Date dateOfFire, User user) {
        this.id = id;
        this.drivingLicenseNum = drivingLicenseNum;
        this.status = status;
        this.licenseEndDate = licenseEndDate;
        this.dateOfFire = dateOfFire;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDateOfFire() {
        return dateOfFire;
    }

    public void setDateOfFire(Date dateOfFire) {
        this.dateOfFire = dateOfFire;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", drivingLicenseNum='" + drivingLicenseNum + '\'' +
                ", status=" + status +
                ", licenseEndDate=" + licenseEndDate +
                ", user=" + user +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Driver driver = (Driver) o;

        if (drivingLicenseNum != null ? !drivingLicenseNum.equals(driver.drivingLicenseNum) : driver.drivingLicenseNum != null)
            return false;
        if (licenseEndDate != null ? !licenseEndDate.equals(driver.licenseEndDate) : driver.licenseEndDate != null)
            return false;
        if (dateOfFire != null ? !dateOfFire.equals(driver.dateOfFire) : driver.dateOfFire != null) return false;
        if (id != null ? !id.equals(driver.id) : driver.id != null) return false;
        if (status != driver.status) return false;
        return user != null ? user.equals(driver.user) : driver.user == null;
    }

    @Override
    public int hashCode() {
        int result = drivingLicenseNum != null ? drivingLicenseNum.hashCode() : 0;
        result = 31 * result + (licenseEndDate != null ? licenseEndDate.hashCode() : 0);
        result = 31 * result + (dateOfFire != null ? dateOfFire.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }
}
