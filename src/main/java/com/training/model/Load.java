package com.training.model;

import com.training.entities.enums.LoadStatus;

public class Load {
    private Integer weight;
    private LoadStatus loadStatus;

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
