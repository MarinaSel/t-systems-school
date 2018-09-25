package com.training.models.entities;
import com.training.models.statuses.DriverStatus;
import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity(name = "driver")
class DriverEntity {
    @Id
    private Integer drivingLicenseNum;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "second_name", nullable = false)
    private String secondName;

    @Column(name = "license_end_date")
    private Date licenseEndDate;

    @Column(name = "status", nullable = false)
    private DriverStatus driverStatus;

    @Column(name = "current_city", nullable = false)
    private String currentCity;

    @ManyToOne
    @JoinColumn(name = "registration_number", nullable = true)
    private Vehicle registrationNumber;

    public DriverEntity(Integer drivingLicenseNum, String firstName, String secondName, Date licenseEndDate, DriverStatus driverStatus, String currentCity) {
        this.drivingLicenseNum = drivingLicenseNum;
        this.firstName = firstName;
        this.secondName = secondName;
        this.licenseEndDate = licenseEndDate;
        this.driverStatus = driverStatus;
        this.currentCity = currentCity;
    }

    protected DriverEntity(){}

    public Integer getDrivingLicenseNum() {
        return drivingLicenseNum;
    }

    public void setDrivingLicenseNum(Integer drivingLicenseNum) {
        this.drivingLicenseNum = drivingLicenseNum;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public Date getLicenseEndDate() {
        return licenseEndDate;
    }

    public void setLicenseEndDate(Date licenseEndDate) {
        this.licenseEndDate = licenseEndDate;
    }

    public DriverStatus getDriverStatus() {
        return driverStatus;
    }

    public void setDriverStatus(DriverStatus driverStatus) {
        this.driverStatus = driverStatus;
    }

    public String getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(String currentCity) {
        this.currentCity = currentCity;
    }

    public Vehicle getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(Vehicle registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DriverEntity driver = (DriverEntity) o;
        return Objects.equals(drivingLicenseNum, driver.drivingLicenseNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(drivingLicenseNum, firstName, secondName, licenseEndDate, driverStatus, currentCity, registrationNumber);
    }

    @Override
    public String toString() {
        return "Driver{" +
                "drivingLicenseNum=" + drivingLicenseNum +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", licenseEndDate=" + licenseEndDate +
                ", driverStatus=" + driverStatus +
                ", currentCity='" + currentCity + '\'' +
                ", registrationNumber=" + registrationNumber +
                '}';
    }
}
