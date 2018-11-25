package com.training.unit;

import com.training.entities.UserEntity;
import com.training.models.User;
import org.junit.Test;

import static com.training.mappers.UserMapper.mapEntityToModel;
import static com.training.mappers.UserMapper.mapModelToEntity;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class UserMapperTest {

    private static final Long ID = 1L;
    private static final String FIRST_NAME = "First name";
    private static final String ANOTHER_FIRST_NAME = "Another first name";
    private static final String LAST_NAME = "Last name";
    private static final String LOGIN = "Login";
    private static final String PASSWORD = "Password";
    private static final UserEntity USER_ENTITY = new UserEntity(ID, FIRST_NAME, LAST_NAME, LOGIN, PASSWORD);
    private static final User USER_MODEL = new User(ID, FIRST_NAME, LAST_NAME, LOGIN, PASSWORD);

    @Test
    public void getModelFromEntity() {
        assertEquals(USER_MODEL, mapEntityToModel(USER_ENTITY));
    }

    @Test
    public void getModelFromEntityWithNotMatchingProperties() {
        UserEntity anotherUserEntity = new UserEntity(ID, ANOTHER_FIRST_NAME, LAST_NAME, LOGIN, PASSWORD);
        assertNotEquals(USER_MODEL, mapEntityToModel(anotherUserEntity));
    }

    @Test
    public void getEntityFromModel() {
        assertEquals(USER_ENTITY, mapModelToEntity(USER_MODEL));
    }

    @Test
    public void getEntityFromModelWithNotMatchingProperties() {
        User anotherUserModel = new User(ID, ANOTHER_FIRST_NAME, LAST_NAME, LOGIN, PASSWORD);
        assertNotEquals(USER_ENTITY, mapModelToEntity(anotherUserModel));
    }
}
