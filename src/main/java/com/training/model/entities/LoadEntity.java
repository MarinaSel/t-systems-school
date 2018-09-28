package com.training.model.entities;
import com.training.model.statuses.LoadStatus;
import javax.persistence.*;

@Entity
public class LoadEntity {
    @Id
    @SequenceGenerator(name = "loads_id_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "loads_id_seq")
    private Long id;

    @Column(name = "status", nullable = false)
    private LoadStatus loadStatus;

    @Column(name = "weight",nullable = false)
    private Integer weight;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", nullable = false)
    private VehicleEntity vehicle;

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private LocationEntity location;

    protected LoadEntity(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LoadStatus getLoadStatus() {
        return loadStatus;
    }

    public void setLoadStatus(LoadStatus loadStatus) {
        this.loadStatus = loadStatus;
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

    public LocationEntity getLocation() {
        return location;
    }

    public void setLocation(LocationEntity location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "LoadEntity{" +
                "id=" + id +
                ", loadStatus=" + loadStatus +
                ", weight=" + weight +
                ", vehicle=" + vehicle +
                ", location=" + location +
                '}';
    }
}
