package com.training.entities.enums;

import com.training.entities.LoadEntity;

/**
 * Enum with load statuses.
 *
 * @see LoadEntity
 */
public enum LoadStatus {

    /**
     * Means that the load is not assigned to any vehicle.
     */
    NOT_ASSIGNED,

    /**
     * Means that the load is assigned to a vehicle, but delivery hasn't started yet.
     */
    ASSIGNED,

    /**
     * Means that delivery of the load has started.
     */
    IN_PROGRESS,

    /**
     * Means that the load has been delivered.
     */
    DONE,

    /**
     * Means that the load delivery was rejected.
     */
    REJECTED
}
