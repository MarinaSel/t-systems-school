package com.training.repositories;

import com.training.entities.LoadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Interface for load extends base JpaRepository with CRUD operations.
 */
@Repository
public interface LoadRepository extends JpaRepository<LoadEntity, Long> {

}
