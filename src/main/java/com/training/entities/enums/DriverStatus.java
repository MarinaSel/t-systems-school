package com.training.entities.enums;

/**
 * Enumeration with driver statuses.
 * @see com.training.entities.DriverEntity
 */
public enum DriverStatus {
    /**
     * Means that driver works but not driving now.
     */
    REST("REST"),

    /**
     * Means that driver fulfills order(s).
     */
    WORK("WORK"),

    /**
     * Means that driver is driving now.
     */
    DRIVING("DRIVING"),
    /**
     * Means that driver hasn't got order(s).
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
