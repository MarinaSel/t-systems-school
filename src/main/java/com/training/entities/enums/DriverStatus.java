package com.training.entities.enums;

import com.training.entities.DriverEntity;

/**
 * Enumeration with driver statuses.
 *
 * @see DriverEntity
 */
public enum DriverStatus {

    /**
     * Means that the driver is not available.
     */
    REST("REST"),

    /**
     * Means that the driver is fulfilling order(s).
     */
    WORK("WORK"),

    /**
     * Means that the driver is driving now.
     */
    DRIVING("DRIVING"),

    /**
     * Means that the driver is available.
     */
    FREE("FREE");

    private final String status;

    DriverStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
