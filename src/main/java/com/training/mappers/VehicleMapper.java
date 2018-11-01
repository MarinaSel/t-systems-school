package com.training.mappers;

import com.training.entities.VehicleEntity;
import com.training.models.Vehicle;

import java.util.LinkedList;
import java.util.List;

/**
 * Utility class that describes static methods for mapping VehicleEntity object from Vehicle object
 * and Vehicle object from VehicleEntity object.
 *
 * @see VehicleEntity
 * @see Vehicle
 */
public final class VehicleMapper {

    private VehicleMapper() {
    }

    /**
     * Method maps VehicleEntity object to Vehicle object.
     *
     * @param vehicleEntity VehicleEntity to be mapped
     * @return vehicle
     */
    public static Vehicle mapEntityToModel(VehicleEntity vehicleEntity) {
        if (vehicleEntity == null) {
            return null;
        }
        Vehicle vehicle = commonFields(vehicleEntity);
        vehicle.setLoads(LoadMapper.mapEntitySetToModelSet(vehicleEntity.getLoads()));
        vehicle.setDrivers(DriverMapper.mapEntitySetToModelSet(vehicleEntity.getDrivers()));

        return vehicle;
    }

    /**
     * Method maps VehicleEntity object to simple Vehicle model without set of loads.
     *
     * @param vehicleEntity VehicleEntity to be mapped
     * @return vehicle
     */
    public static Vehicle mapEntityToSimpleModel(VehicleEntity vehicleEntity) {

        if (vehicleEntity == null) {
            return null;
        }
        Vehicle vehicle = commonFields(vehicleEntity);
        vehicle.setDrivers(DriverMapper.mapEntitySetToModelSet(vehicleEntity.getDrivers()));

        return vehicle;
    }

    /**
     * Method maps common fields for simple Vehicle model and Vehicle model with set of loads.
     *
     * @param vehicleEntity VehicleEntity to be mapped
     * @return vehicle
     */
    private static Vehicle commonFields(VehicleEntity vehicleEntity) {

        if (vehicleEntity == null) {
            return null;
        }
        Vehicle vehicle = new Vehicle();

        vehicle.setId(vehicleEntity.getId());
        vehicle.setRegistrationNumber(vehicleEntity.getRegistrationNumber());
        vehicle.setCapacity(vehicleEntity.getCapacity());
        vehicle.setStatus(vehicleEntity.getStatus());
        vehicle.setCreationDate(vehicleEntity.getCreationDate());
        vehicle.setModel(vehicleEntity.getModel());
        vehicle.setDateOfIssue(vehicleEntity.getDateOfIssue());

        return vehicle;
    }

    /**
     * Method maps Vehicle object to VehicleEntity object.
     *
     * @param vehicle Vehicle to be mapped
     * @return vehicleEntity
     */
    public static VehicleEntity mapModelToEntity(Vehicle vehicle) {
        if (vehicle == null) {
            return null;
        }
        VehicleEntity vehicleEntity = new VehicleEntity();

        vehicleEntity.setId(vehicle.getId());
        vehicleEntity.setRegistrationNumber(vehicle.getRegistrationNumber());
        vehicleEntity.setCapacity(vehicle.getCapacity());
        vehicleEntity.setStatus(vehicle.getStatus());
        vehicleEntity.setCreationDate(vehicle.getCreationDate());
        vehicleEntity.setModel(vehicle.getModel());
        vehicleEntity.setDateOfIssue(vehicle.getDateOfIssue());

        return vehicleEntity;
    }

    /**
     * Method maps list of VehicleEntity objects to list of Vehicle objects.
     *
     * @param vehicleEntities list of VehicleEntity objects
     * @return vehicles list of Vehicle objects
     */
    public static List<Vehicle> mapEntityListToModelList(List<VehicleEntity> vehicleEntities) {
        List<Vehicle> vehicles = new LinkedList<>();

        for (VehicleEntity vehicleEntity : vehicleEntities) {
            vehicles.add(mapEntityToModel(vehicleEntity));
        }

        return vehicles;
    }

}
