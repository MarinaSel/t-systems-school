package com.training.entities.enums;

/**
 * Enum with load statuses.
 *
 * @see com.training.entities.LoadEntity
 */
public enum LoadStatus {
    /**
     * Means that the vehicle is not assigned to load.
     */
    NOT_ASSIGNED,

    /**
     * Means that the vehicle is assigned to load, but delivery hasn't started.
     */
    ASSIGNED,
    /**
     * Means that delivery has started.
     */
    IN_PROGRESS,

    /**
     * Means that load delivered.
     */
    DONE
}
