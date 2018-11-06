package com.training.entities;

import com.training.entities.enums.DriverStatus;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

import static com.training.entities.enums.DriverStatus.FREE;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.SEQUENCE;
import static javax.persistence.TemporalType.DATE;

@Entity
@Table(name = "drivers")
public class DriverEntity extends BaseEntity {

    @Id
    @SequenceGenerator(name = "drivers_id_seq", sequenceName = "drivers_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "drivers_id_seq")
    private Long id;

    @NotNull(message = "Driving license number cannot be null")
    @Size(max = 50, message = "Driving license number is invalid")
    @Column(name = "driving_license_num", nullable = false, unique = true, length = 10)
    private String drivingLicenseNum;

    @NotNull(message = "License end date cannot be null")
    @Temporal(DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "license_end_date", nullable = false)
    private Date licenseEndDate;

    @Enumerated(STRING)
    @Column(name = "status", nullable = false)
    private DriverStatus status;

    @Column(name = "current_city")
    private String currentCity;

    @NotNull(message = "User cannot be null")
    @OneToOne
    private UserEntity user;

    public DriverEntity() {
    }

    public DriverEntity(String drivingLicenseNum, Date licenseEndDate, DriverStatus status, UserEntity user) {
        this.drivingLicenseNum = drivingLicenseNum;
        this.licenseEndDate = licenseEndDate;
        this.status = status;
        this.user = user;
    }

    @PrePersist
    public void prePersist() {
        status = FREE;
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

    public String getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(String currentCity) {
        this.currentCity = currentCity;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DriverEntity that = (DriverEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (drivingLicenseNum != null ? !drivingLicenseNum.equals(that.drivingLicenseNum) : that.drivingLicenseNum != null)
            return false;
        if (licenseEndDate != null ? !licenseEndDate.equals(that.licenseEndDate) : that.licenseEndDate != null)
            return false;
        if (status != that.status) return false;
        if (currentCity != null ? !currentCity.equals(that.currentCity) : that.currentCity != null) return false;
        return user != null ? user.equals(that.user) : that.user == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (drivingLicenseNum != null ? drivingLicenseNum.hashCode() : 0);
        result = 31 * result + (licenseEndDate != null ? licenseEndDate.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (currentCity != null ? currentCity.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DriverEntity{" +
                "id=" + id +
                ", drivingLicenseNum='" + drivingLicenseNum + '\'' +
                ", licenseEndDate=" + licenseEndDate +
                ", status=" + status +
                ", currentCity='" + currentCity + '\'' +
                '}';
    }
}
