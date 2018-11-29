package com.training.mappers;

import com.training.entities.LocationEntity;
import com.training.models.Location;

import java.util.LinkedList;
import java.util.List;

/**
 * Utility class that provides static methods for mapping LocationEntity objects to Location objects
 * and Location objects to LocationEntity objects.
 *
 * @see com.training.entities.LocationEntity
 * @see Location
 */
public class LocationMapper {

    public LocationMapper() {
    }

    /**
     * Maps Location object to LocationEntity object.
     *
     * @param location Location object to be mapped
     * @return LocationEntity object - result of mapping
     */
    public static LocationEntity mapModelToEntity(Location location) {
        if (location == null) {
            return null;
        }
        LocationEntity locationEntity = new LocationEntity();
        locationEntity.setId(location.getId());
        locationEntity.setName(location.getName());
        locationEntity.setLatitude(location.getLatitude());
        locationEntity.setLongitude(location.getLongitude());
        return locationEntity;
    }

    /**
     * Maps LocationEntity object to Location object.
     *
     * @param locationEntity LocationEntity object to be mapped
     * @return Location object - result of mapping
     */
    public static Location mapEntityToModel(LocationEntity locationEntity) {
        if (locationEntity == null) {
            return null;
        }
        Location location = new Location();
        location.setId(locationEntity.getId());
        location.setName(locationEntity.getName());
        location.setLatitude(locationEntity.getLatitude());
        location.setLongitude(locationEntity.getLongitude());
        return location;
    }

    /**
     * Maps List of LocationEntity objects to List of Location objects.
     *
     * @param locationEntities List of LocationEntity objects to be mapped
     * @return List of Location objects - result of mapping
     */
    public static List<Location> mapEntityListToModelList(List<LocationEntity> locationEntities) {
        if (locationEntities == null) {
            return null;
        }
        List<Location> locations = new LinkedList<>();
        for (LocationEntity locationEntity : locationEntities) {
            locations.add(mapEntityToModel(locationEntity));
        }
        return locations;
    }
}
