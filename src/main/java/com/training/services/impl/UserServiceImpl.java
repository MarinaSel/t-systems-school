package com.training.services.impl;

import com.training.entities.UserEntity;
import com.training.models.User;
import com.training.repositories.UserRepository;
import com.training.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.training.mappers.UserMapper.mapEntityToModel;
import static com.training.mappers.UserMapper.mapModelToEntity;

@Service
public class UserServiceImpl implements UserService {

    private final static Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public User save(User user) {
        UserEntity userEntity = mapModelToEntity(user);
        Long userId = user.getId();
        if (userId != null && userEntity.getPassword().equals(userRepository.getOne(userId).getPassword())) {
            userEntity.setPassword(user.getPassword());
        } else {
            userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        }
        if (userId == null) {
            LOGGER.info("Created user with id = {}", userId);
        } else {
            LOGGER.info("Updated user with id = {}", userId);
        }
        userRepository.saveAndFlush(userEntity);
        return mapEntityToModel(userEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public User findByLogin(String login) {
        UserEntity userEntity = userRepository.findByLogin(login);
        if (userEntity == null) {
            LOGGER.info("User with login = {} was not found", login);
        } else {
            LOGGER.info("Found user by login = {}", login);
        }
        return mapEntityToModel(userEntity);
    }
}
