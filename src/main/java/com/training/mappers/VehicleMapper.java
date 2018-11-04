package com.training.mappers;

import com.training.entities.VehicleEntity;
import com.training.models.Vehicle;

import java.util.LinkedList;
import java.util.List;

/**
 * Utility class that provides static methods for mapping VehicleEntity objects to Vehicle objects
 * and Vehicle objects to VehicleEntity objects.
 *
 * @see VehicleEntity
 * @see Vehicle
 */
public final class VehicleMapper {

    private VehicleMapper() {
    }

    /**
     * Maps VehicleEntity object to Vehicle object.
     *
     * @param vehicleEntity VehicleEntity object to be mapped
     * @return Vehicle object - result of mapping
     */
    public static Vehicle mapEntityToModel(VehicleEntity vehicleEntity) {
        if (vehicleEntity == null) {
            return null;
        }
        Vehicle vehicle = commonFields(vehicleEntity);
        vehicle.setLoads(LoadMapper.mapEntitySetToModelSet(vehicleEntity.getLoads()));
        return vehicle;
    }

    /**
     * Maps VehicleEntity object to simple Vehicle object without set of loads.
     *
     * @param vehicleEntity VehicleEntity object to be mapped
     * @return Vehicle object - result of mapping
     */
    static Vehicle mapEntityToSimpleModel(VehicleEntity vehicleEntity) {
        if (vehicleEntity == null) {
            return null;
        }
        return commonFields(vehicleEntity);
    }

    /**
     * Maps common fields for Vehicle object and sinple Vehicle object.
     *
     * @param vehicleEntity VehicleEntity object to be mapped
     * @return Vehicle object - result of mapping
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
        vehicle.setIssueDate(vehicleEntity.getIssueDate());
        vehicle.setPrimaryDriver(DriverMapper.mapEntityToModel(vehicleEntity.getPrimaryDriver()));
        vehicle.setCoDriver(DriverMapper.mapEntityToModel(vehicleEntity.getCoDriver()));
        return vehicle;
    }

    /**
     * Maps Vehicle object to VehicleEntity object.
     *
     * @param vehicle Vehicle object to be mapped
     * @return VehicleEntity object - result of mapping
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
        vehicleEntity.setIssueDate(vehicle.getIssueDate());
        vehicleEntity.setPrimaryDriver(DriverMapper.mapModelToEntity(vehicle.getPrimaryDriver()));
        vehicleEntity.setCoDriver(DriverMapper.mapModelToEntity(vehicle.getCoDriver()));
        return vehicleEntity;
    }

    /**
     * Maps List of VehicleEntity objects to List of Vehicle objects.
     *
     * @param vehicleEntities List of VehicleEntity objects to be mapped
     * @return List of Vehicle objects - result of mapping
     */
    public static List<Vehicle> mapEntityListToModelList(List<VehicleEntity> vehicleEntities) {
        List<Vehicle> vehicles = new LinkedList<>();
        for (VehicleEntity vehicleEntity : vehicleEntities) {
            vehicles.add(mapEntityToModel(vehicleEntity));
        }
        return vehicles;
    }
}
