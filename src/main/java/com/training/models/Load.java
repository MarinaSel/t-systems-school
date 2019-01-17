package com.training.models;

import com.training.entities.enums.LoadStatus;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class Load extends BaseModel {

    @NotNull(message = "Title cannot be null")
    private String title;

    @NotNull(message = "Description cannot be null")
    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dayOfDelivery;

    @NotNull(message = "Weight cannot be null")
    private Integer weight;

    private Long id;
    private LoadStatus status;
    private Vehicle vehicle;
    private Location pickUpLocation;
    private Location deliveryLocation;
    private History history;

    public Load() {
    }

    public Load(Long id, String title, String description, Date dayOfDelivery, Integer weight, LoadStatus status,
                Vehicle vehicle) {
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

    public Location getPickUpLocation() {
        return pickUpLocation;
    }

    public void setPickUpLocation(Location pickUpLocation) {
        this.pickUpLocation = pickUpLocation;
    }

    public Location getDeliveryLocation() {
        return deliveryLocation;
    }

    public void setDeliveryLocation(Location deliveryLocation) {
        this.deliveryLocation = deliveryLocation;
    }

    public History getHistory() {
        return history;
    }

    public void setHistory(History history) {
        this.history = history;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Load load = (Load) o;

        if (title != null ? !title.equals(load.title) : load.title != null) return false;
        if (description != null ? !description.equals(load.description) : load.description != null) return false;
        if (dayOfDelivery != null ? !dayOfDelivery.equals(load.dayOfDelivery) : load.dayOfDelivery != null)
            return false;
        if (weight != null ? !weight.equals(load.weight) : load.weight != null) return false;
        if (id != null ? !id.equals(load.id) : load.id != null) return false;
        if (status != load.status) return false;
        if (vehicle != null ? !vehicle.equals(load.vehicle) : load.vehicle != null) return false;
        if (pickUpLocation != null ? !pickUpLocation.equals(load.pickUpLocation) : load.pickUpLocation != null)
            return false;
        return deliveryLocation != null ? deliveryLocation.equals(load.deliveryLocation) : load.deliveryLocation == null;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (dayOfDelivery != null ? dayOfDelivery.hashCode() : 0);
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (vehicle != null ? vehicle.hashCode() : 0);
        result = 31 * result + (pickUpLocation != null ? pickUpLocation.hashCode() : 0);
        result = 31 * result + (deliveryLocation != null ? deliveryLocation.hashCode() : 0);
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
