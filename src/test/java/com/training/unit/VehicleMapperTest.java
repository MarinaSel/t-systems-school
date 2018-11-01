package com.training.unit;

import com.training.entities.VehicleEntity;
import com.training.entities.enums.VehicleStatus;
import com.training.mappers.VehicleMapper;
import com.training.models.Vehicle;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;


public class VehicleMapperTest {

    private Date date;

    @Before
    public void init() {
        date = new Date();
    }

    @Test
    public void getModelFromEntity() {
        Vehicle vehicle = new Vehicle(1L, "564791j", 7, VehicleStatus.FREE, "model", date);
        VehicleEntity vehicleEntity = new VehicleEntity("model", date, "564791j", 7, VehicleStatus.FREE);
        vehicleEntity.setId(1L);
        assertEquals(vehicle, VehicleMapper.mapEntityToModel(vehicleEntity));

    }

    @Test
    public void getModelFromEntityWithNotMatchingProperties() {
        Vehicle vehicle = new Vehicle(1L, "564791j", 7, VehicleStatus.FREE, "model", date);
        VehicleEntity vehicleEntity = new VehicleEntity("model", date, "1258977", 7, VehicleStatus.FREE);
        vehicleEntity.setId(1L);
        assertNotEquals(vehicle, VehicleMapper.mapEntityToModel(vehicleEntity));
    }

    @Test
    public void getEntityFromModel() {
        Vehicle vehicle = new Vehicle(1L, "564791j", 7, VehicleStatus.FREE, "model", date);
        VehicleEntity vehicleEntity = new VehicleEntity("model", date, "564791j", 7, VehicleStatus.FREE);
        vehicleEntity.setId(1L);
        assertEquals(vehicleEntity, VehicleMapper.mapModelToEntity(vehicle));
    }

    @Test
    public void getEntityFromModelWithNotMatchingProperties() {
        Vehicle vehicle = new Vehicle(1L, "564791j", 7, VehicleStatus.FREE, "model", date);
        VehicleEntity vehicleEntity = new VehicleEntity("model", date, "1111111", 7, VehicleStatus.FREE);
        vehicleEntity.setId(1L);
        assertNotEquals(vehicleEntity, VehicleMapper.mapModelToEntity(vehicle));
    }

    @Test
    public void getEntityFromNullModel() {
        assertNull(VehicleMapper.mapModelToEntity(null));
    }

    @Test
    public void getModelFromNullEntity() {
        assertNull(VehicleMapper.mapEntityToModel(null));
    }
}
