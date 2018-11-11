package com.training.services.impl;

import com.training.entities.UserEntity;
import com.training.models.User;
import com.training.repositories.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.training.mappers.UserMapper.mapEntityToModel;
import static com.training.mappers.UserMapper.mapModelToEntity;

/**
 * Class that provides methods for operations with users.
 *
 * @see User
 */
@Service
public class UserService {

    private final static Logger LOGGER = LogManager.getLogger(UserService.class);

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Saves user.
     *
     * @param user User object to be saved
     * @return User object - result of saving
     */
    @Transactional
    User save(User user) {
        UserEntity userEntity = mapModelToEntity(user);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userRepository.saveAndFlush(userEntity);
        LOGGER.info("Created user with id = {}", userEntity.getId());
        return mapEntityToModel(userEntity);
    }

    /**
     * Finds user by login.
     *
     * @param login String object with login for search
     * @return found User object with specified login
     */
    @Transactional(readOnly = true)
    User findByLogin(String login) {
        UserEntity userEntity = userRepository.findByLogin(login);
        LOGGER.info("Found user by login = {}", userEntity.getLogin());
        return mapEntityToModel(userEntity);
    }
}
