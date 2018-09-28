package com.training.repository;

import com.training.model.entities.LoadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoadRepository extends JpaRepository<LoadEntity, Long> {
}
