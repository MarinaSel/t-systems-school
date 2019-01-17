package com.training.entities;

import com.training.entities.enums.VehicleStatus;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static com.training.entities.enums.VehicleStatus.FREE;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.SEQUENCE;
import static javax.persistence.TemporalType.DATE;

@Entity
@Table(name = "vehicles")
public class VehicleEntity extends BaseEntity {

    @Id
    @SequenceGenerator(name = "vehicles_id_seq", sequenceName = "vehicles_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "vehicles_id_seq")
    private Long id;

    @Column(name = "model", nullable = false, length = 20)
    private String model;

    @Temporal(DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Issue date cannot be null")
    @Column(name = "date_of_issue", nullable = false)
    private Date issueDate;

    @Size(max = 7, message = "Registration number is invalid")
    @NotNull(message = "Registration number cannot be null")
    @Column(name = "registration_number", nullable = false, length = 7, unique = true)
    private String registrationNumber;

    @NotNull(message = "Capacity cannot be null")
    @Column(name = "capacity", nullable = false)
    private Integer capacity;

    @Enumerated(STRING)
    @Column(name = "status", nullable = false)
    private VehicleStatus status;

    @Column(name = "current_city")
    private String currentCity;

    @OneToOne
    @JoinColumn(name = "primary_driver_id")
    private DriverEntity primaryDriver;

    @OneToOne
    @JoinColumn(name = "co_driver_id")
    private DriverEntity coDriver;

    @OneToMany(mappedBy = "vehicle")
    private Set<LoadEntity> loads = new HashSet<>();

    public VehicleEntity() {
    }

    public VehicleEntity(Long id, String model, Date issueDate, String registrationNumber, Integer capacity,
                         VehicleStatus status) {
        this.id = id;
        this.model = model;
        this.issueDate = issueDate;
        this.registrationNumber = registrationNumber;
        this.capacity = capacity;
        this.status = status;
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

    public DriverEntity getPrimaryDriver() {
        return primaryDriver;
    }

    public void setPrimaryDriver(DriverEntity primaryDriver) {
        this.primaryDriver = primaryDriver;
    }

    public DriverEntity getCoDriver() {
        return coDriver;
    }

    public void setCoDriver(DriverEntity coDriver) {
        this.coDriver = coDriver;
    }

    public Set<LoadEntity> getLoads() {
        return loads;
    }

    public void setLoads(Set<LoadEntity> loads) {
        this.loads = loads;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VehicleEntity that = (VehicleEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (model != null ? !model.equals(that.model) : that.model != null) return false;
        if (issueDate != null ? !issueDate.equals(that.issueDate) : that.issueDate != null) return false;
        if (registrationNumber != null ? !registrationNumber.equals(that.registrationNumber) : that.registrationNumber != null)
            return false;
        if (capacity != null ? !capacity.equals(that.capacity) : that.capacity != null) return false;
        if (status != that.status) return false;
        if (currentCity != null ? !currentCity.equals(that.currentCity) : that.currentCity != null) return false;
        if (primaryDriver != null ? !primaryDriver.equals(that.primaryDriver) : that.primaryDriver != null)
            return false;
        if (coDriver != null ? !coDriver.equals(that.coDriver) : that.coDriver != null) return false;
        return loads != null ? loads.equals(that.loads) : that.loads == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (issueDate != null ? issueDate.hashCode() : 0);
        result = 31 * result + (registrationNumber != null ? registrationNumber.hashCode() : 0);
        result = 31 * result + (capacity != null ? capacity.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (currentCity != null ? currentCity.hashCode() : 0);
        result = 31 * result + (primaryDriver != null ? primaryDriver.hashCode() : 0);
        result = 31 * result + (coDriver != null ? coDriver.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "VehicleEntity{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", issueDate=" + issueDate +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", capacity=" + capacity +
                ", status=" + status +
                ", currentCity='" + currentCity + '\'' +
                ", primaryDriver=" + primaryDriver +
                ", coDriver=" + coDriver +
                '}';
    }
}
