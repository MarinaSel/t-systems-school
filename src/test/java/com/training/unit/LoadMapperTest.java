package com.training.unit;

import com.training.entities.LoadEntity;
import com.training.models.Load;
import org.junit.Test;

import java.util.Date;

import static com.training.entities.enums.LoadStatus.NOT_ASSIGNED;
import static com.training.mappers.LoadMapper.mapEntityToModel;
import static com.training.mappers.LoadMapper.mapModelToEntity;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;


public class LoadMapperTest {

    private static final Long ID = 1L;
    private static final String TITLE = "Title";
    private static final String ANOTHER_TITLE = "Another title";
    private static final String DESCRIPTION = "Description";
    private static final Integer WEIGHT = 7;
    private static final Date DATE = new Date();
    private static final LoadEntity LOAD_ENTITY =
            new LoadEntity(ID, TITLE, DESCRIPTION, DATE, NOT_ASSIGNED, WEIGHT, null);
    private static final Load LOAD_MODEL = new Load(ID, TITLE, DESCRIPTION, DATE, WEIGHT, NOT_ASSIGNED, null);

    @Test
    public void getModelFromEntity() {
        assertEquals(LOAD_MODEL, mapEntityToModel(LOAD_ENTITY));
    }

    @Test
    public void getModelFromEntityWithNotMatchingProperties() {
        LoadEntity anotherLoadEntity = new LoadEntity(ID, ANOTHER_TITLE, DESCRIPTION, DATE,
                NOT_ASSIGNED, WEIGHT, null);
        assertNotEquals(LOAD_MODEL, mapEntityToModel(anotherLoadEntity));
    }

    @Test
    public void getEntityFromModel() {
        assertEquals(LOAD_ENTITY, mapModelToEntity(LOAD_MODEL));
    }

    @Test
    public void getEntityFromModelWithNotMatchingProperties() {
        Load anotherLoadModel = new Load(ID, ANOTHER_TITLE, DESCRIPTION, DATE, WEIGHT, NOT_ASSIGNED, null);
        assertNotEquals(LOAD_ENTITY, mapModelToEntity(anotherLoadModel));
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
