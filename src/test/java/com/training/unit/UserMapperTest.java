package com.training.unit;

import com.training.entities.UserEntity;
import com.training.mappers.UserMapper;
import com.training.models.User;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

public class UserMapperTest {

    @Test
    public void getModelFromEntity() {
        User user = new User(1L, "FirstName", "LastName", "Login", "Password");
        UserEntity userEntity = new UserEntity("FirstName", "LastName", "Login", "Password");
        userEntity.setId(1L);
        assertEquals(user, UserMapper.mapEntityToModel(userEntity));
    }

    @Test
    public void getModelFromEntityWithNotMatchingProperties() {
        User user = new User(1L, "FirstName", "LastName", "Login", "Password");
        UserEntity userEntity = new UserEntity("Name", "LastName", "Login", "Password");
        userEntity.setId(1L);
        assertNotEquals(user, UserMapper.mapEntityToModel(userEntity));
    }

    @Test
    public void getEntityFromModel() {
        User user = new User(1L, "FirstName", "LastName", "Login", "Password");
        UserEntity userEntity = new UserEntity("FirstName", "LastName", "Login", "Password");
        userEntity.setId(1L);
        assertEquals(userEntity, UserMapper.mapModelToEntity(user));
    }

    @Test
    public void getEntityFromModelWithNotMatchingProperties() {
        User user = new User(1L, "FirstName", "LastName", "Login", "Password");
        UserEntity userEntity = new UserEntity("Name", "LastName", "Login", "Password");
        userEntity.setId(1L);
        assertNotEquals(userEntity, UserMapper.mapModelToEntity(user));
    }

    @Test
    public void getEntityFromNullModel() {
        assertNull(UserMapper.mapModelToEntity(null));
    }

    @Test
    public void getModelFromNullEntity() {
        assertNull(UserMapper.mapEntityToModel(null));
    }
}
