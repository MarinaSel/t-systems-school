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

    @PrePersist
    public void prePersist(){
        if(status == null){
            if (vehicle == null){
                status = LoadStatus.NOT_ASSIGNED;
            }
            else{
                status = LoadStatus.IN_PROGRESS;
            }
        }
    }

    @PreUpdate
    public void preUpdate(){
        if(vehicle != null){
            status = LoadStatus.IN_PROGRESS;
        }
    }

    public LoadEntity(){}

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

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
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
