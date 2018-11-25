package com.training.repositories;

import com.training.entities.DriverEntity;
import com.training.entities.UserEntity;
import com.training.entities.enums.DriverStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interface that extends JpaRepository and defines methods for operations with drivers.
 *
 * @see JpaRepository
 * @see DriverEntity
 */
@Repository
public interface DriverRepository extends JpaRepository<DriverEntity, Long> {

    /**
     * Finds all drivers with specified status.
     *
     * @param status String object with status for search
     * @return List of DriverEntity objects with specified status
     */
    List<DriverEntity> findAllByStatus(DriverStatus status);

    /**
     * Finds driver by driving license number.
     *
     * @param drivingLicenseNum String object with driving license number for search
     * @return DriverEntity object with specified driving license number
     */
    DriverEntity findByDrivingLicenseNum(String drivingLicenseNum);

    /**
     * Finds driver assigned to specified user.
     *
     * @param userEntity UserEntity object for search
     * @return DriverEntity object assigned to specified UserEntity object
     */
    DriverEntity findByUser(UserEntity userEntity);

    /**
     * Marks driver as free and removes him from vehicle.
     * Used when completing delivery or assigning new vehicle to load.
     *
     * @param primaryDriverId Long object - id of primary driver
     * @param coDriverId      Long object - id of co-driver
     */
    @Modifying
    @Query("update DriverEntity d set d.status = 'FREE' where d.id = ?1 or d.id = ?2")
    void setFree(Long primaryDriverId, Long coDriverId);

    /**
     * Marks drivers as working. Used when starting delivery.
     *
     * @param primaryDriverId Long object - id of primary driver
     * @param coDriverId      Long object - id of co-driver
     */
    @Modifying
    @Query("update DriverEntity d set d.status = 'WORKING' where d.id = ?1 or d.id = ?2")
    void setWorking(Long primaryDriverId, Long coDriverId);

    /**
     * Marks driver as assigned. Used when driver is assigned to vehicle.
     *
     * @param id String object - driving license number of driver
     */
    @Modifying
    @Query("update DriverEntity d set d.status = 'ASSIGNED' where d.id = ?1")
    void setAssigned(Long id);

    /**
     * Marks driver as fired. Used when firing driver.
     *
     * @param id Long object - id of driver to be fired
     */
    @Modifying
    @Query("update DriverEntity d set d.status = 'FIRED', d.dateOfFire = current_date where d.id = ?1")
    void setFired(Long id);

    /**
     * Marks driver as resting.
     *
     * @param id Long object - id of resting driver
     */
    @Modifying
    @Query("update DriverEntity d set d.status = 'REST' where d.id = ?1")
    void setRest(Long id);

    /**
     * Marks driver as free.
     *
     * @param id Long object - id of driver to be set free
     */
    @Modifying
    @Query("update DriverEntity d set d.status = 'FREE' where d.id = ?1")
    void setFree(Long id);
}
