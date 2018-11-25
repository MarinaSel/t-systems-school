package com.training.services;

import com.training.models.User;

/**
 * Interface that defines methods for operations with users.
 *
 * @see User
 */
public interface UserService {

    /**
     * Saves user.
     *
     * @param user User object to be saved
     * @return User object - result of saving
     */
    User save(User user);

    /**
     * Finds user by login.
     *
     * @param login String object with login for search
     * @return found User object with specified login
     */
    User findByLogin(String login);
}
