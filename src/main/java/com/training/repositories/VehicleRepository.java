package com.training.repositories;

import com.training.entities.VehicleEntity;
import com.training.entities.enums.VehicleStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interface for vehicle repository extends JpaRepository with CRUD operations.
 */
@Repository
public interface VehicleRepository extends JpaRepository<VehicleEntity, Long> {

    List<VehicleEntity> findAllByStatus(VehicleStatus status);

    VehicleEntity findVehicleEntityByRegistrationNumber(String registrationNumber);
}
