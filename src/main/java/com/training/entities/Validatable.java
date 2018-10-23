package com.training.entities;

import com.training.listeners.ValidatingListener;

import javax.persistence.EntityListeners;

@EntityListeners(ValidatingListener.class)
public interface Validatable {
}
