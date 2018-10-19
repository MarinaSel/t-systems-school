package com.training.entities;

import com.training.entities.enums.LoadStatus;

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

import java.util.Date;

@Entity
@Table(name = "loads")
public class LoadEntity extends BaseEntity {

    @Id
    @SequenceGenerator(name = "loads_id_seq", sequenceName = "loads_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "loads_id_seq")
    private Long id;

    @NotNull(message = "Title cannot be null")
    @Column(name = "title", nullable = false)
    private String title;

    @NotNull(message = "Description cannot be null")
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull(message = "Day od delivery cannot be null")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_of_delivery", nullable = false)
    private Date dayOfDelivery;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private LoadStatus status;

    @NotNull(message = "Weight cannot be null")
    @Column(name = "weight",nullable = false)
    private Integer weight;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private VehicleEntity vehicle;

    public LoadEntity(){}

    public LoadEntity(@NotNull(message = "Title cannot be null") String title, @NotNull(message = "Description cannot be null") String description, @NotNull(message = "Day od delivery cannot be null") Date dayOfDelivery, @NotNull LoadStatus status, @NotNull(message = "Weight cannot be null") Integer weight, VehicleEntity vehicle) {
        this.title = title;
        this.description = description;
        this.dayOfDelivery = dayOfDelivery;
        this.status = status;
        this.weight = weight;
        this.vehicle = vehicle;
    }

    @PrePersist
    public void prePersist(){
        if(status == null){
            if (vehicle == null){
                status = LoadStatus.NOT_ASSIGNED;
            }
            else{
                status = LoadStatus.ASSIGNED;
            }
        }
    }

    @PreUpdate
    public void preUpdate(){
        if(vehicle != null && status != LoadStatus.IN_PROGRESS){
            status = LoadStatus.ASSIGNED;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDayOfDelivery() {
        return dayOfDelivery;
    }

    public void setDayOfDelivery(Date dayOfDelivery) {
        this.dayOfDelivery = dayOfDelivery;
    }

    public LoadStatus getStatus() {
        return status;
    }

    public void setStatus(LoadStatus status) {
        this.status = status;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
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

        LoadEntity that = (LoadEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (dayOfDelivery != null ? !dayOfDelivery.equals(that.dayOfDelivery) : that.dayOfDelivery != null)
            return false;
        if (status != that.status) return false;
        if (weight != null ? !weight.equals(that.weight) : that.weight != null) return false;
        return vehicle != null ? vehicle.equals(that.vehicle) : that.vehicle == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (dayOfDelivery != null ? dayOfDelivery.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        result = 31 * result + (vehicle != null ? vehicle.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "LoadEntity{" +
                "id=" + id +
                ", status=" + status +
                ", weight=" + weight +
                ", vehicle=" + vehicle +
                '}';
    }
}
