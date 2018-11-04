package com.training.listeners;

import com.training.entities.Validatable;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationException;
import java.util.Set;

public class ValidatingListener {

    /**
     * Validates entities before persisting or updating them.
     *
     * @param validatable entity to be validated
     */
    @PrePersist
    @PreUpdate
    public void validate(Validatable validatable) {
        Set<ConstraintViolation<Validatable>> violations
                = Validation.buildDefaultValidatorFactory().getValidator().validate(validatable);

        if (violations != null && violations.size() > 0) {
            ConstraintViolation<Validatable> violation = violations.iterator().next();
            String exceptionMessage = violation.getRootBeanClass().getSimpleName() + ": " + violation.getMessage();
            throw new ValidationException(exceptionMessage);
        }
    }
}