package com.training.entities;

import com.training.listeners.ValidatingListener;

import javax.persistence.EntityListeners;

/**
 * Interface to be implemented by validatable entities.
 *
 * @see ValidatingListener
 */
@EntityListeners(ValidatingListener.class)
public interface Validatable {
}