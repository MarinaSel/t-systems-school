package com.training.mappers;

import com.training.entities.DriverEntity;
import com.training.entities.VehicleEntity;
import com.training.models.Vehicle;

import java.util.LinkedList;
import java.util.List;

public final class VehicleMapper {

    private VehicleMapper(){};

    public static Vehicle getModelFromEntity(VehicleEntity vehicleEntity){
        if (vehicleEntity == null){
            return null;
        }
        Vehicle vehicle = commonFields(vehicleEntity);
        vehicle.setLoads(LoadMapper.getModelSetFromEntitySet(vehicleEntity.getLoads()));
        vehicle.setDrivers(DriverMapper.getModelSetFromEntitySet(vehicleEntity.getDrivers()));

        return vehicle;
    }

    public static Vehicle getSimpleModelFromEntity(VehicleEntity vehicleEntity){

        if(vehicleEntity == null){
            return null;
        }
        Vehicle vehicle = commonFields(vehicleEntity);
        vehicle.setDrivers(DriverMapper.getModelSetFromEntitySet(vehicleEntity.getDrivers()));

        return vehicle;
    }

    private static Vehicle commonFields(VehicleEntity vehicleEntity){

        Vehicle vehicle = new Vehicle();

        vehicle.setId(vehicleEntity.getId());
        vehicle.setRegistrationNumber(vehicleEntity.getRegistrationNumber());
        vehicle.setCapacity(vehicleEntity.getCapacity());
        vehicle.setStatus(vehicleEntity.getStatus());
        vehicle.setCreationDate(vehicleEntity.getCreationDate());

        return vehicle;
    }

    public static VehicleEntity getEntityFromModel(Vehicle vehicle){
        if (vehicle == null){
            return null;
        }
        VehicleEntity vehicleEntity = new VehicleEntity();

        vehicleEntity.setId(vehicle.getId());
        vehicleEntity.setRegistrationNumber(vehicle.getRegistrationNumber());
        vehicleEntity.setCapacity(vehicle.getCapacity());
        vehicleEntity.setStatus(vehicle.getStatus());
        vehicleEntity.setCreationDate(vehicle.getCreationDate());
        vehicleEntity.setDrivers(DriverMapper.getEntitySetFromModelSet(vehicle.getDrivers()));

        return vehicleEntity;
    }

    public static List<Vehicle> getModelListFromEntityList(List<VehicleEntity> vehicleEntities){
        List<Vehicle> vehicles = new LinkedList<>();

        for (VehicleEntity vehicleEntity : vehicleEntities) {
            vehicles.add(getModelFromEntity(vehicleEntity));
        }
        return vehicles;
    }

}
