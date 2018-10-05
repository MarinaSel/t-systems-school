package com.training.repositories;

import com.training.entities.LoadEntity;
import com.training.entities.enums.LoadStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoadRepository extends JpaRepository<LoadEntity, Long> {
    List<LoadEntity> findByLoadStatus(LoadStatus status);


}
