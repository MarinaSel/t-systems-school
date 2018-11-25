package com.training.repositories;

import com.training.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface that extends JpaRepository and defines methods for operations with users.
 *
 * @see JpaRepository
 * @see UserEntity
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    /**
     * Finds user by login.
     *
     * @param login String object with login for search
     * @return UserEntity with specified login
     */
    UserEntity findByLogin(String login);
}
