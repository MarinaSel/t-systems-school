package com.training.model.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
public class LocationEntity {
    @Id
    @SequenceGenerator(name = "locations_id_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "locations_id_seq")
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "latitude", nullable = false)
    private Double latitude;
    @Column(name = "longitude", nullable = false)
    private Double longitude;

    @OneToMany(mappedBy = "location")
    private Set<LoadEntity> loads;

    @OneToMany(mappedBy = "location")
    private Set<VehicleEntity> vehicles;

    protected LocationEntity(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Set<LoadEntity> getLoads() {
        return loads;
    }

    public void setLoads(Set<LoadEntity> loads) {
        this.loads = loads;
    }

    public Set<VehicleEntity> getVehicles() {
        return vehicles;
    }

    public void setVehicles(Set<VehicleEntity> vehicles) {
        this.vehicles = vehicles;
    }

    @Override
    public String toString() {
        return "LocationEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", loads=" + loads +
                ", vehicles=" + vehicles +
                '}';
    }
}
