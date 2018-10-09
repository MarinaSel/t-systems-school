package com.training.models;

import com.training.entities.enums.LoadStatus;

public class Load extends BaseModel{

    private Long id;
    private Integer weight;
    private LoadStatus loadStatus;


    public Load(){}

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

    public LoadStatus getStatus() {
        return loadStatus;
    }

    public void setStatus(LoadStatus loadStatus) {
        this.loadStatus = loadStatus;
    }
}
