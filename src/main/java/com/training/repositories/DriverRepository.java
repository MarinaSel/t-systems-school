package com.training.repositories;

import com.training.model.entities.DriverEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<DriverEntity, Long> {

    public List<DriverEntity> findByFirstNameAndLastName(String firstName, String lastName);
}
