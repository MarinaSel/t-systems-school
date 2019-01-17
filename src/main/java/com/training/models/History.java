package com.training.models;

public class History {

    private Driver primaryDriver;
    private Driver coDriver;
    private Vehicle vehicle;

    public Driver getPrimaryDriver() {
        return primaryDriver;
    }

    public void setPrimaryDriver(Driver primaryDriver) {
        this.primaryDriver = primaryDriver;
    }

    public Driver getCoDriver() {
        return coDriver;
    }

    public void setCoDriver(Driver coDriver) {
        this.coDriver = coDriver;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        History history = (History) o;

        if (primaryDriver != null ? !primaryDriver.equals(history.primaryDriver) : history.primaryDriver != null)
            return false;
        if (coDriver != null ? !coDriver.equals(history.coDriver) : history.coDriver != null) return false;
        return vehicle != null ? vehicle.equals(history.vehicle) : history.vehicle == null;
    }

    @Override
    public int hashCode() {
        int result = primaryDriver != null ? primaryDriver.hashCode() : 0;
        result = 31 * result + (coDriver != null ? coDriver.hashCode() : 0);
        result = 31 * result + (vehicle != null ? vehicle.hashCode() : 0);
        return result;
    }
}
