package com.training.entities.enums;

public enum DriverStatus {
    REST("REST"), WORK("WORK"), DRIVING("DRIVING"), FREE("FREE");

    private final String status;

    DriverStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}
