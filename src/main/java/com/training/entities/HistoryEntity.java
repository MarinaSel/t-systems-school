package com.training.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "history")
public class HistoryEntity {
    @Id
    @SequenceGenerator(name = "history_id_seq", sequenceName = "history_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "history_id_seq")
    private Long id;

    @OneToOne
    @JoinColumn(name = "primary_driver_history_id")
    private DriverEntity primaryDriver;

    @OneToOne
    @JoinColumn(name = "co_driver_history_id")
    private DriverEntity coDriver;

    @OneToOne
    @JoinColumn(name = "vehicle_history_id")
    private VehicleEntity vehicle;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

        HistoryEntity that = (HistoryEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (primaryDriver != null ? !primaryDriver.equals(that.primaryDriver) : that.primaryDriver != null)
            return false;
        if (coDriver != null ? !coDriver.equals(that.coDriver) : that.coDriver != null) return false;
        return vehicle != null ? vehicle.equals(that.vehicle) : that.vehicle == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (primaryDriver != null ? primaryDriver.hashCode() : 0);
        result = 31 * result + (coDriver != null ? coDriver.hashCode() : 0);
        result = 31 * result + (vehicle != null ? vehicle.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "HistoryEntity{" +
                "id=" + id +
                ", primaryDriver=" + primaryDriver +
                ", coDriver=" + coDriver +
                ", vehicle=" + vehicle +
                '}';
    }
}
