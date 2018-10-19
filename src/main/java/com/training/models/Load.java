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

    public Load(Long id, @NotNull(message = "Title cannot be null") String title, @NotNull(message = "Description cannot be null") String description, @NotNull(message = "Day od delivery cannot be null") Date dayOfDelivery, @NotNull(message = "Weight cannot be null") Integer weight, LoadStatus status, Vehicle vehicle) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dayOfDelivery = dayOfDelivery;
        this.weight = weight;
        this.status = status;
        this.vehicle = vehicle;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Load load = (Load) o;

        if (id != null ? !id.equals(load.id) : load.id != null) return false;
        if (title != null ? !title.equals(load.title) : load.title != null) return false;
        if (description != null ? !description.equals(load.description) : load.description != null) return false;
        if (dayOfDelivery != null ? !dayOfDelivery.equals(load.dayOfDelivery) : load.dayOfDelivery != null)
            return false;
        if (weight != null ? !weight.equals(load.weight) : load.weight != null) return false;
        if (status != load.status) return false;
        return vehicle != null ? vehicle.equals(load.vehicle) : load.vehicle == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (dayOfDelivery != null ? dayOfDelivery.hashCode() : 0);
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (vehicle != null ? vehicle.hashCode() : 0);
        return result;
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
