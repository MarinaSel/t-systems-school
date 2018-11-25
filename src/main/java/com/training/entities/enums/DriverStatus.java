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
     * Means that the driver is assigned to vehicle.
     */
    ASSIGNED("ASSIGNED"),

    /**
     * Means that the driver is fulfilling order(s).
     */
    WORKING("WORKING"),

    /**
     * Means that the driver is available.
     */
    FREE("FREE"),

    /**
     * Means that the driver has been fired.
     */
    FIRED("FIRED");

    private final String status;

    DriverStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
