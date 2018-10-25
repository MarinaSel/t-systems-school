package com.training.entities;

import com.training.listeners.DateSettingListener;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import java.util.Date;

/**
 * This class describes the general field for DriverEntity, VehicleEntity and LoadEntity.
 * @see DriverEntity
 * @see VehicleEntity
 * @see LoadEntity
 */
@MappedSuperclass
@EntityListeners(DateSettingListener.class)
public abstract class BaseEntity {

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
