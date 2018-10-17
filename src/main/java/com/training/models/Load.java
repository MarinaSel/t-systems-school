package com.training.models;

import com.training.entities.enums.LoadStatus;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;

import java.util.Date;

public class Load extends BaseModel{

    private Long id;

    @NotNull(message = "Title cannot be null")
    private String title;

    @NotNull(message = "Description cannot be null")
    private String description;

    @NotNull(message = "Day od delivery cannot be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dayOfDelivery;

    @NotNull(message = "Weight cannot be null")
    private Integer weight;
    private LoadStatus status;
    private Vehicle vehicle;

    public Load(){}

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

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public LoadStatus getStatus() {
        return status;
    }

    public void setStatus(LoadStatus loadStatus) {
        this.status = loadStatus;
    }

    @Override
    public String toString() {
        return "Load{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", dayOfDelivery=" + dayOfDelivery +
                ", weight=" + weight +
                ", status=" + status +
                ", vehicle=" + vehicle +
                '}';
    }
}
