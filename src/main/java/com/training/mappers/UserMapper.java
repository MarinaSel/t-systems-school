package com.training.mappers;

import com.training.entities.UserEntity;
import com.training.models.User;

/**
 * Utility class that describes static methods for mapping UserEntity objects from User objects
 * and User objects from UserEntity objects.
 *
 * @see UserEntity
 * @see User
 */
public final class UserMapper {

    private UserMapper() {
    }

    /**
     * Method maps user to userEntity.
     *
     * @param user User object to be mapped
     * @return userEntity
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
     * Method maps userEntity to user.
     *
     * @param userEntity UserEntity to be mapped
     * @return user
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

