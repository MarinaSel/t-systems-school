package com.training.entities;
import com.training.entities.enums.LoadStatus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "loads")
public class LoadEntity {
    @Id
    @SequenceGenerator(name = "loads_id_seq", sequenceName = "loads_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "loads_id_seq")
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private LoadStatus loadStatus;

    @NotNull
    @Column(name = "weight",nullable = false)
    private Integer weight;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", nullable = false)
    private VehicleEntity vehicle;

    public LoadEntity(){}

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

    @Override
    public String toString() {
        return "LoadEntity{" +
                "id=" + id +
                ", loadStatus=" + loadStatus +
                ", weight=" + weight +
                ", vehicle=" + vehicle +
                '}';
    }
}
