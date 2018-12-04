package com.training.repositories;

import com.training.entities.DriverEntity;
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
     * or assigning new vehicle to load.
     *
     * @param id Long object - id of vehicle
     */
    @Modifying
    @Query("update VehicleEntity v set v.status = 'FREE', v.primaryDriver = null, v.coDriver = null where v.id = ?1")
    void setFree(Long id);

    /**
     * Marks vehicle as broken. Used when driver reject delivery due to a broken vehicle.
     *
     * @param id Long object - id of broken vehicle
     */
    @Modifying
    @Query("update VehicleEntity v set v.status = 'BROKEN', v.primaryDriver = null, v.coDriver = null where v.id = ?1 ")
    void setBrokenVehicle(Long id);

    /**
     * Marks vehicle as working. Used when starting delivery.
     *
     * @param id Long object - id of vehicle
     */
    @Modifying
    @Query("update VehicleEntity v set v.status = 'WORKING' where v.id = ?1")
    void setWorking(Long id);

    /**
     * Finds vehicle by driver.
     *
     * @param driverEntity DriverEntity object for search
     * @return found VehicleEntity object
     */
    @Query("select v from VehicleEntity v where v.primaryDriver = ?1 or v.coDriver = ?1")
    VehicleEntity findVehicleByDriver(DriverEntity driverEntity);

    /**
     * Marks vehicle as broken.
     *
     * @param id Long object - id of broken vehicle
     */
    @Modifying
    @Query("update VehicleEntity v set v.status = 'BROKEN' where v.id = ?1")
    void setBroken(Long id);
}
