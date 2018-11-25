package com.training.listeners;

import com.training.entities.Validatable;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationException;
import java.util.Set;

import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;

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

        if (isNotEmpty(violations)) {
            ConstraintViolation<Validatable> violation = violations.iterator().next();
            String exceptionMessage = violation.getRootBeanClass().getSimpleName() + ": " + violation.getMessage();
            throw new ValidationException(exceptionMessage);
        }
    }
}