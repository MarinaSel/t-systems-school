package com.training.unit;


import com.training.entities.LocationEntity;
import com.training.models.Location;
import org.junit.Test;

import static com.training.mappers.LocationMapper.mapEntityToModel;
import static com.training.mappers.LocationMapper.mapModelToEntity;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

public class LocationMapperTest {

    private static final Long ID = 1L;
    private static final String NAME = "Voronezh";
    private static final String ANOTHER_NAME = "Belgorod";
    private static final Double LATITUDE = 58.06655;
    private static final Double LONGITUDE = 59.00322;
    private static final LocationEntity LOCATION_ENTITY = new LocationEntity(ID, NAME, LATITUDE, LONGITUDE);
    private static final Location LOCATION_MODEL = new Location(ID, NAME, LATITUDE, LONGITUDE);

    @Test
    public void getModelFromEntity() {
        assertEquals(LOCATION_MODEL, mapEntityToModel(LOCATION_ENTITY));
    }

    @Test
    public void getModelFromEntityWithNotMatchingProperties() {
        LocationEntity anotherLocationEntity = new LocationEntity(ID, ANOTHER_NAME, LATITUDE, LONGITUDE);
        assertNotEquals(LOCATION_MODEL, mapEntityToModel(anotherLocationEntity));
    }

    @Test
    public void getEntityFromModel() {
        assertEquals(LOCATION_ENTITY, mapModelToEntity(LOCATION_MODEL));
    }

    @Test
    public void getEntityFromModelWithNotMatchingProperties() {
        Location anotherLocationModel = new Location(ID, ANOTHER_NAME, LATITUDE, LONGITUDE);
        assertNotEquals(LOCATION_MODEL, mapModelToEntity(anotherLocationModel));
    }

    @Test
    public void getEntityFromNullModel() {
        assertNull(mapModelToEntity(null));
    }

    @Test
    public void getModelFromNullEntity() {
        assertNull(mapEntityToModel(null));
    }
}
