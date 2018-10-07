package com.training.entities;
import com.training.entities.enums.DriverStatus;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "drivers")
public class DriverEntity {

    @Id
    @SequenceGenerator(name = "drivers_id_seq", initialValue = 1, sequenceName = "drivers_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "drivers_id_seq")
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "driving_license_num", nullable = false, unique = true, length = 50)
    private String drivingLicenseNum;

    @NotNull
    @Size(max = 50)
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @NotNull
    @Size(max = 50)
    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "license_end_date", nullable = false)
    private Date licenseEndDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private DriverStatus status;

    @Column(name = "current_city")
    private String currentCity;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private VehicleEntity vehicle;

    @PrePersist
    @PreUpdate
    public void prePersist() {
        if(status == null)
            status = DriverStatus.UNDEFINED;
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
