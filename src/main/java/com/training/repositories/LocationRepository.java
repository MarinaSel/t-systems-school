package com.training.repositories;

import com.training.entities.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface that extends JpaRepository and defines methods for operations with locations.
 *
 * @see JpaRepository
 * @see LocationEntity
 */
@Repository
public interface LocationRepository extends JpaRepository<LocationEntity, Long> {

    /**
     * Finds location by name.
     *
     * @param name String object with name for search
     * @return LocationEntity object with specified name
     */
    LocationEntity findByName(String name);
}
