package com.training.repositories;

import com.training.entities.DriverEntity;
import com.training.entities.enums.DriverStatus;
import com.training.models.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface DriverRepository extends JpaRepository<DriverEntity, Long> {

    List<DriverEntity> findAllByStatus(DriverStatus status);

    DriverEntity findByDrivingLicenseNum(String drivingLicenseNum);

}
