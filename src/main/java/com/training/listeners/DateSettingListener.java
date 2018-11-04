package com.training.listeners;

import com.training.entities.BaseEntity;

import javax.persistence.PrePersist;
import java.util.Date;


public class DateSettingListener {

    /**
     * Sets creation date before persisting entities.
     *
     * @param entity to set creation date
     * @see BaseEntity
     */
    @PrePersist
    public void setCreationDate(BaseEntity entity) {
        entity.setCreationDate(new Date());
    }
}
