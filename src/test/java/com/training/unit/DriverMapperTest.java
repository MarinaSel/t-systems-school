package com.training.unit;

import com.training.entities.DriverEntity;
import com.training.models.Driver;
import org.junit.Test;

import java.util.Date;

import static com.training.entities.enums.DriverStatus.FREE;
import static com.training.mappers.DriverMapper.mapEntityToModel;
import static com.training.mappers.DriverMapper.mapModelToEntity;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

public class DriverMapperTest {

    private static final Long ID = 1L;
    private static final String LICENCE_NUM = "87654kk";
    private static final String ANOTHER_LICENSE_NUM = "1121212";
    private static final Date DATE = new Date();
    private static final DriverEntity DRIVER_ENTITY = new DriverEntity(ID, LICENCE_NUM, DATE, FREE, DATE, null);
    private static final Driver DRIVER_MODEL = new Driver(ID, LICENCE_NUM, FREE, DATE, DATE, null);

    @Test
    public void getModelFromEntity() {
        assertEquals(DRIVER_MODEL, mapEntityToModel(DRIVER_ENTITY));
    }

    @Test
    public void getModelFromEntityWithNotMatchingProperties() {
        DriverEntity anotherDriverEntity = new DriverEntity(ID, ANOTHER_LICENSE_NUM, DATE, FREE, DATE, null);
        assertNotEquals(DRIVER_MODEL, mapEntityToModel(anotherDriverEntity));
    }

    @Test
    public void getEntityFromModel() {
        assertEquals(DRIVER_ENTITY, mapModelToEntity(DRIVER_MODEL));
    }

    @Test
    public void getEntityFromModelWithNotMatchingProperties() {
        Driver anotherDriverModel = new Driver(ID, ANOTHER_LICENSE_NUM, FREE, DATE, DATE, null);
        assertNotEquals(DRIVER_ENTITY, mapModelToEntity(anotherDriverModel));
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
