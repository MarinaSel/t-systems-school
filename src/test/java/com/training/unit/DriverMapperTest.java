package com.training.unit;

import com.training.entities.DriverEntity;
import com.training.entities.UserEntity;
import com.training.entities.enums.DriverStatus;
import com.training.mappers.DriverMapper;
import com.training.models.Driver;
import com.training.models.User;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

public class DriverMapperTest {

    private static Date date;
    private static UserEntity userEntity;
    private static User user;

    @BeforeClass
    public static void init() {
        userEntity = new UserEntity("first", "last", "login", "password");
        user = new User(1L, "first", "last", "login", "password");
        date = new Date();
    }


    @Test
    public void getModelFromEntity() {
        userEntity.setId(1L);
        Driver driver = new Driver(1L, "863jhg", DriverStatus.FREE, date, user);
        DriverEntity driverEntity = new DriverEntity("863jhg", date, DriverStatus.FREE, userEntity);
        driverEntity.setId(1L);
        assertEquals(driver, DriverMapper.mapEntityToModel(driverEntity));
    }

    @Test
    public void getModelFromEntityWithNotMatchingProperties() {
        Driver driver = new Driver(1L, "863jhg", DriverStatus.FREE, date, user);
        DriverEntity driverEntity = new DriverEntity("8634jhg", date, DriverStatus.FREE, userEntity);
        driverEntity.setId(1L);
        assertNotEquals(driver, DriverMapper.mapEntityToModel(driverEntity));
    }

    @Test
    public void getEntityFromModel() {
        Driver driver = new Driver(1L, "863jhg", DriverStatus.FREE, date, user);
        DriverEntity driverEntity = new DriverEntity("863jhg", date, DriverStatus.FREE, userEntity);
        driverEntity.setId(1L);
        assertEquals(driverEntity, DriverMapper.mapModelToEntity(driver));
    }

    @Test
    public void getEntityFromModelWithNotMatchingProperties() {
        Driver driver = new Driver(1L, "863jhg", DriverStatus.FREE, date, user);
        DriverEntity driverEntity = new DriverEntity("863jhg", date, DriverStatus.REST, userEntity);
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
