package com.training.entities;

import com.training.entities.enums.DriverStatus;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.Date;

@Entity
@Table(name = "drivers")
public class DriverEntity extends BaseEntity {

    @Id
    @SequenceGenerator(name = "drivers_id_seq", sequenceName = "drivers_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "drivers_id_seq")
    private Long id;

    @NotNull(message = "Driving license number cannot be null")
    @Size(max = 50, message = "Driving license number is invalid")
    @Column(name = "driving_license_num", nullable = false, unique = true, length = 50)
    private String drivingLicenseNum;

    @NotNull(message = "First name cannot be null")
    @Size(max = 50, message = "First name is invalid")
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @NotNull(message = "Last name cannot be null")
    @Size(max = 50, message = "Last name is invalid")
    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @NotNull(message = "License end date cannot be null")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "license_end_date", nullable = false)
    private Date licenseEndDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private DriverStatus status;

    @Column(name = "current_city")
    private String currentCity;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private VehicleEntity vehicle;

    public DriverEntity(){}

    public DriverEntity(@NotNull(message = "Driving license number cannot be null") @Size(max = 50, message = "Driving license number is invalid") String drivingLicenseNum, @NotNull(message = "First name cannot be null") @Size(max = 50, message = "First name is invalid") String firstName, @NotNull(message = "Last name cannot be null") @Size(max = 50, message = "Last name is invalid") String lastName, @NotNull(message = "License end date cannot be null") Date licenseEndDate, @NotNull DriverStatus status, VehicleEntity vehicle) {
        this.drivingLicenseNum = drivingLicenseNum;
        this.firstName = firstName;
        this.lastName = lastName;
        this.licenseEndDate = licenseEndDate;
        this.status = status;
        this.vehicle = vehicle;
    }

    @PrePersist
    public void prePersist() {
        status = DriverStatus.FREE;
    }

    @PreUpdate
    public void preUpdate() {
        if (vehicle != null) {
            status = DriverStatus.WORK;
        }
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

    public String getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(String currentCity) {
        this.currentCity = currentCity;
    }

    public VehicleEntity getVehicle() {
        return vehicle;
    }

    public void setVehicle(VehicleEntity vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DriverEntity that = (DriverEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (drivingLicenseNum != null ? !drivingLicenseNum.equals(that.drivingLicenseNum) : that.drivingLicenseNum != null)
            return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (licenseEndDate != null ? !licenseEndDate.equals(that.licenseEndDate) : that.licenseEndDate != null)
            return false;
        if (status != that.status) return false;
        return vehicle != null ? vehicle.equals(that.vehicle) : that.vehicle == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (drivingLicenseNum != null ? drivingLicenseNum.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (licenseEndDate != null ? licenseEndDate.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (currentCity != null ? currentCity.hashCode() : 0);
        result = 31 * result + (vehicle != null ? vehicle.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DriverEntity{" +
                "id=" + id +
                ", drivingLicenseNum='" + drivingLicenseNum + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", licenseEndDate=" + licenseEndDate +
                ", status=" + status +
                ", currentCity='" + currentCity + '\'' +
                ", vehicle=" + vehicle +
                '}';
    }
}
