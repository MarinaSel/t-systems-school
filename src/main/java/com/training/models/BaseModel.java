package com.training.models;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


public abstract class BaseModel {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date creationDate;

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
