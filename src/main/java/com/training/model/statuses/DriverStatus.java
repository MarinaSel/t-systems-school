package com.training.model.statuses;

import javax.persistence.Embeddable;

@Embeddable
public enum DriverStatus {
    REST, WORK, DRIVING;
}
