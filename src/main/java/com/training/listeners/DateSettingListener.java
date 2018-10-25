package com.training.listeners;

import com.training.entities.BaseEntity;

import javax.persistence.PrePersist;

import java.util.Date;


public class DateSettingListener {
    /**
     * This method is used for automatically setting creation date before other actions.
     * @see BaseEntity
     * @param entity
     */
    @PrePersist
    public void setCreationDate(BaseEntity entity){
        entity.setCreationDate(new Date());
    }
}
