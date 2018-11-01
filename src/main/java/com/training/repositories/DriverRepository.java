package com.training.repositories;

import com.training.entities.DriverEntity;
import com.training.entities.UserEntity;
import com.training.entities.enums.DriverStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interface for driver repository extends base JpaRepository with CRUD operations.
 */
@Repository
public interface DriverRepository extends JpaRepository<DriverEntity, Long> {

    List<DriverEntity> findAllByStatus(DriverStatus status);

    DriverEntity findByDrivingLicenseNum(String drivingLicenseNum);

    DriverEntity findByUser(UserEntity userEntity);

}
