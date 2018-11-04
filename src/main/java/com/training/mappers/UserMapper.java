package com.training.mappers;

import com.training.entities.UserEntity;
import com.training.models.User;

/**
 * Utility class that provides static methods for mapping UserEntity objects to User objects
 * and User objects to UserEntity objects.
 *
 * @see UserEntity
 * @see User
 */
public final class UserMapper {

    private UserMapper() {
    }

    /**
     * Maps User object to UserEntity object.
     *
     * @param user User object to be mapped
     * @return UserEntity object - result of mapping
     */
    public static UserEntity mapModelToEntity(User user) {
        if (user == null) {
            return null;
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setId(user.getId());
        userEntity.setLogin(user.getLogin());
        userEntity.setPassword(user.getPassword());
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        return userEntity;
    }

    /**
     * Maps UserEntity object to User object.
     *
     * @param userEntity UserEntity object to be mapped
     * @return User object - result of mapping
     */
    public static User mapEntityToModel(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }
        User user = new User();
        user.setId(userEntity.getId());
        user.setLogin(userEntity.getLogin());
        user.setPassword(userEntity.getPassword());
        user.setFirstName(userEntity.getFirstName());
        user.setLastName(userEntity.getLastName());
        return user;
    }
}

