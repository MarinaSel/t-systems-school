package com.training.unit;

import com.training.entities.VehicleEntity;
import com.training.models.Vehicle;
import org.junit.Test;

import java.util.Date;

import static com.training.entities.enums.VehicleStatus.FREE;
import static com.training.mappers.VehicleMapper.mapEntityToModel;
import static com.training.mappers.VehicleMapper.mapModelToEntity;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;


public class VehicleMapperTest {

    private static final Long ID = 1L;
    private static final String REG_NUMBER = "555gggg";
    private static final String ANOTHER_REG_NUMBER = "iiiii111";
    private static final Integer CAPACITY = 7;
    private static final String MODEL = "model";
    private static final Date DATE = new Date();
    private static final VehicleEntity vehicleEntity = new VehicleEntity(ID, MODEL, DATE, REG_NUMBER, CAPACITY, FREE);
    private static final Vehicle vehicleModel = new Vehicle(ID, REG_NUMBER, CAPACITY, FREE, MODEL, DATE);

    @Test
    public void getModelFromEntity() {
        assertEquals(vehicleModel, mapEntityToModel(vehicleEntity));
    }

    @Test
    public void getModelFromEntityWithNotMatchingProperties() {
        VehicleEntity anotherVehicleEntity = new VehicleEntity(ID, MODEL, DATE, ANOTHER_REG_NUMBER, CAPACITY, FREE);
        assertNotEquals(vehicleModel, mapEntityToModel(anotherVehicleEntity));
    }

    @Test
    public void getEntityFromModel() {
        assertEquals(vehicleEntity, mapModelToEntity(vehicleModel));
    }

    @Test
    public void getEntityFromModelWithNotMatchingProperties() {
        Vehicle anotherVehicleModel = new Vehicle(ID, ANOTHER_REG_NUMBER, CAPACITY, FREE, MODEL, DATE);
        assertNotEquals(vehicleEntity, mapModelToEntity(anotherVehicleModel));
    }

    @Test
    public void getEntityFromNullModel() {
        assertNull(mapModelToEntity(null));
    }

    @Test
    public void getModelFromNullEntity() {
        assertNull(mapEntityToModel(null));
    }
}
