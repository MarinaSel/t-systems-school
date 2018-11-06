package com.training.repositories;

import com.training.entities.VehicleEntity;
import com.training.entities.enums.VehicleStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
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
     *
     * @param registrationNumber String object with registration number for search
     * @return VehicleEntity object with specified registration number
     */
    VehicleEntity findVehicleEntityByRegistrationNumber(String registrationNumber);

    /**
     * Marks vehicle as free. Used when completing delivery
     * and all the loads were delivered.
     *
     * @param id Long object - id of vehicle
     */
    @Modifying
    @Query("update VehicleEntity v set v.status = 'FREE', v.primaryDriver = null, v.coDriver = null where v.id = ?1")
    void setFree(Long id);

    /**
     * Marks vehicle as working. Used when starting delivery.
     *
     * @param id Long object - id of vehicle
     */
    @Modifying
    @Query("update VehicleEntity v set v.status = 'WORKING' where v.id = ?1")
    void setWorking(Long id);
}
