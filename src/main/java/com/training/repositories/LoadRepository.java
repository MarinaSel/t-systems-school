package com.training.repositories;

import com.training.entities.LoadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Interface that extends JpaRepository and defines methods for operations with loads.
 *
 * @see JpaRepository
 * @see LoadEntity
 */
@Repository
public interface LoadRepository extends JpaRepository<LoadEntity, Long> {

}
