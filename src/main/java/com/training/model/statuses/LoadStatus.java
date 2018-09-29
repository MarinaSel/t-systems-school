package com.training.model.statuses;

import javax.persistence.Embeddable;

@Embeddable
public enum LoadStatus {
    CREATED, NOT_ASSIGNED, IN_PROGRESS, DONE;
}
