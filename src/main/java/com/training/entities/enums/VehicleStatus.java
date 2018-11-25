package com.training.entities.enums;

import com.training.entities.VehicleEntity;

/**
 * Enum with vehicle statuses.
 *
 * @see VehicleEntity
 */
public enum VehicleStatus {

    /**
     * Means that the vehicle is available.
     */
    FREE("FREE"),

    /**
     * Means that the vehicle is delivering load(s).
     */
    WORKING("WORKING"),

    /**
     * Means that the vehicle is broken and not available.
     */
    BROKEN("BROKEN");

    private final String status;

    VehicleStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
