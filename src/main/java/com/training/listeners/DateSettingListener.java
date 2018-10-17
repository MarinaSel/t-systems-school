package com.training.listeners;

import com.training.entities.BaseEntity;

import javax.persistence.PrePersist;

import java.util.Date;

public class DateSettingListener {
    @PrePersist
    public void setCreationDate(BaseEntity entity){
        entity.setCreationDate(new Date());
    }
}
