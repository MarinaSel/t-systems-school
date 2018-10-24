package com.training.entities.enums;

public enum VehicleStatus {
    FREE("FREE"), WORKING("WORKING"), BROKEN("BROKEN");

    private final String status;

    VehicleStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
