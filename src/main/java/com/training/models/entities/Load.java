package com.training.models.entities;
import com.training.models.statuses.LoadStatus;
import javax.persistence.*;
import java.util.Objects;

@Entity
class Load {
    @Id
    @SequenceGenerator(name = "loads_id_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "loads_id_seq")
    private Long id;

    @Column(name = "status", nullable = false)
    private LoadStatus loadStatus;

    @Column(name = "weight",nullable = false)
    private Integer weight;

    @ManyToOne
    @JoinColumn(name = "registration_number", nullable = true)
    private Vehicle registrationNumber;

    public Load(LoadStatus loadStatus, Integer weight, Vehicle vehicle) {
        this.loadStatus = loadStatus;
        this.weight = weight;
    }

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

    public Vehicle getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(Vehicle registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    @Override
    public String toString() {
        return "Load{" +
                "id=" + id +
                ", loadStatus=" + loadStatus +
                ", weight=" + weight +
                ", registrationNumber=" + registrationNumber +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Load load = (Load) o;

        if (id != null ? !id.equals(load.id) : load.id != null) return false;
        if (loadStatus != load.loadStatus) return false;
        if (weight != null ? !weight.equals(load.weight) : load.weight != null) return false;
        return registrationNumber != null ? registrationNumber.equals(load.registrationNumber) : load.registrationNumber == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (loadStatus != null ? loadStatus.hashCode() : 0);
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        result = 31 * result + (registrationNumber != null ? registrationNumber.hashCode() : 0);
        return result;
    }
}
