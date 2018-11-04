package com.training.entities;

import com.training.listeners.DateSettingListener;
import com.training.listeners.ValidatingListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import java.util.Date;

import static javax.persistence.TemporalType.DATE;

/**
 * Class that provides a common creation date field for entities.
 */
@MappedSuperclass
@EntityListeners({DateSettingListener.class, ValidatingListener.class})
public abstract class BaseEntity implements Validatable {

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(DATE)
    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
