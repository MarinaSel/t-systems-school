package com.training.models;

import com.training.entities.enums.LoadStatus;

public class Load {
    private Long id;
    private Integer weight;
    private LoadStatus loadStatus;

    public Load(){}

    public Load(Long id, Integer weight, LoadStatus loadStatus) {
        this.id = id;
        this.weight = weight;
        this.loadStatus = loadStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public LoadStatus getLoadStatus() {
        return loadStatus;
    }

    public void setLoadStatus(LoadStatus loadStatus) {
        this.loadStatus = loadStatus;
    }
}
