package com.training.entities.enums;

/**
 * Enum with vehicle statuses.
 *
 * @see com.training.entities.VehicleEntity
 */
public enum VehicleStatus {
    /**
     * Means that vehicle is ready to load.
     */
    FREE("FREE"),

    /**
     * Means that vehicle is delivering the load.
     */
    WORKING("WORKING"),

    /**
     * Means that vehicle is broken and can't be loaded.
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
