package com.training.repositories;

import com.training.entities.VehicleEntity;
import com.training.entities.enums.VehicleStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interface that extends JpaRepository and defines methods for operations with vehicles.
 *
 * @see JpaRepository
 * @see VehicleEntity
 */
@Repository
public interface VehicleRepository extends JpaRepository<VehicleEntity, Long> {

    /**
     * Finds all vehicles with specified status.
     *
     * @param status String object with status for search
     * @return List of VehicleEntity objects with specified status
     */
    List<VehicleEntity> findAllByStatus(VehicleStatus status);

    /**
     * Finds vehicle by registration number.
     * @param registrationNumber String object with registration number for search
     * @return VehicleEntity object with specified registration number
     */
    VehicleEntity findVehicleEntityByRegistrationNumber(String registrationNumber);
}
