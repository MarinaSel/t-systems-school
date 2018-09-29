package com.training.model.entities;
import com.training.model.statuses.DriverStatus;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "drivers")
public class DriverEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "driving_license_num", nullable = false)
    private String drivingLicenseNum;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "second_name", nullable = false)
    private String secondName;

    @Temporal(TemporalType.DATE)
    @Column(name = "license_end_date")
    private Date licenseEndDate;

    @Embedded
    @Column(name = "status", nullable = false)
    private DriverStatus driverStatus;

    @Column(name = "current_city", nullable = false)
    private String currentCity;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", nullable = true)
    private VehicleEntity vehicle;

    protected DriverEntity() {
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
                ", drivingLicenseNum=" + drivingLicenseNum +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", licenseEndDate=" + licenseEndDate +
                ", driverStatus=" + driverStatus +
                ", currentCity='" + currentCity + '\'' +
                ", vehicle=" + vehicle +
                '}';
    }
}
