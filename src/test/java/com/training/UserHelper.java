package com.training;

import com.training.entities.UserEntity;

public final class UserHelper {

    public static final String LOGIN = "login";
    private static final Long USER_ID = 1L;
    private static final String FIRST_NAME = "John";
    private static final String LAST_NAME = "Doe";
    private static final String PASSWORD = "password";

    private UserHelper() {
    }

    public static UserEntity getUser() {
        return new UserEntity(USER_ID, FIRST_NAME, LAST_NAME, LOGIN, PASSWORD);
    }
}
