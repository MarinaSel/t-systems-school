package com.training.repositories;

import com.training.entities.DriverEntity;
import com.training.entities.UserEntity;
import com.training.entities.enums.DriverStatus;
import org.springframework.data.jpa.repository.JpaRepository;
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

}
