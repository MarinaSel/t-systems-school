package com.training.repositories;

import com.training.entities.LoadEntity;
import com.training.entities.VehicleEntity;
import com.training.entities.enums.LoadStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Interface that extends JpaRepository and defines methods for operations with loads.
 *
 * @see JpaRepository
 * @see LoadEntity
 */
@Repository
public interface LoadRepository extends JpaRepository<LoadEntity, Long> {

    /**
     * Marks load as done and removes its vehicle.
     * Used when completing delivery.
     *
     * @param id Long object - id of load
     */
    @Modifying
    @Query("update LoadEntity l set l.vehicle = null, l.status = 'DONE', l.dayOfDelivery = current_date " +
            "where l.id = ?1")
    void setDone(Long id);

    /**
     * Marks all the loads assigned to vehicle as being in progress.
     * Used when starting delivery.
     *
     * @param vehicleEntity VehicleEntity object - assigned vehicle
     */
    @Modifying
    @Query("update LoadEntity l set l.status = 'IN_PROGRESS' where l.vehicle = ?1")
    void setInProgress(VehicleEntity vehicleEntity);

    /**
     * Marks loads assigned to vehicle as done and removes their vehicle.
     * Used when completing delivery for all loads.
     *
     * @param vehicleEntity VehicleEntity object - vehicle delivering loads
     */
    @Modifying
    @Query("update LoadEntity l set l.vehicle = null, l.status = 'DONE', l.dayOfDelivery = current_date " +
            "where l.vehicle = ?1")
    void setDone(VehicleEntity vehicleEntity);

    /**
     * Marks loads assigned to vehicle as rejected and removes their vehicle.
     * Used when rejecting delivery.
     *
     * @param vehicleEntity VehicleEntity object - vehicle supposed to deliver loads
     */
    @Modifying
    @Query("update LoadEntity l set vehicle = null, l.status = 'REJECTED' where l.vehicle = ?1")
    void setRejected(VehicleEntity vehicleEntity);

    /**
     * Finds all loads with specified status.
     *
     * @param status String object with status for search
     * @return List of LoadEntities objects with specified status
     */
    List<LoadEntity> findAllByStatus(LoadStatus status);
}
