package com.training.models;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Class that provides a common creation date field for model classes.
 *
 */
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
