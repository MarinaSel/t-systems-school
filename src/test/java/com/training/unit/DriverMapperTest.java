package com.training.unit;

import com.training.entities.DriverEntity;
import com.training.entities.enums.DriverStatus;
import com.training.mappers.DriverMapper;
import com.training.models.Driver;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

public class DriverMapperTest {

    private Date date;

    @Before
    public void initDate() {
        date = new Date();
    }

    @Test
    public void getModelFromEntity() {
        Driver driver = new Driver(1L, "863jhg", DriverStatus.FREE, null, date);
        DriverEntity driverEntity = new DriverEntity("863jhg", date, DriverStatus.FREE, null, null);
        driverEntity.setId(1L);
        assertEquals(driver, DriverMapper.mapEntityToModel(driverEntity));
    }

    @Test
    public void getModelFromEntityWithNotMatchingProperties() {
        Driver driver = new Driver(1L, "863jhg", DriverStatus.FREE, null, date);
        DriverEntity driverEntity = new DriverEntity("8634jhg", date, DriverStatus.FREE, null, null);
        driverEntity.setId(1L);
        assertNotEquals(driver, DriverMapper.mapEntityToModel(driverEntity));
    }

    @Test
    public void getEntityFromModel() {
        Driver driver = new Driver(1L, "863jhg", DriverStatus.FREE, null, date);
        DriverEntity driverEntity = new DriverEntity("863jhg", date, DriverStatus.FREE, null, null);
        driverEntity.setId(1L);
        assertEquals(driverEntity, DriverMapper.mapModelToEntity(driver));
    }

    @Test
    public void getEntityFromModelWithNotMatchingProperties() {
        Driver driver = new Driver(1L, "863jhg", DriverStatus.FREE, null, date);
        DriverEntity driverEntity = new DriverEntity("863jhg", date, DriverStatus.REST, null, null);
        driverEntity.setId(1L);
        assertNotEquals(driverEntity, DriverMapper.mapModelToEntity(driver));
    }

    @Test
    public void getEntityFromNullModel() {
        assertNull(DriverMapper.mapModelToEntity(null));
    }

    @Test
    public void getModelFromNullEntity() {
        assertNull(DriverMapper.mapEntityToModel(null));
    }


}
