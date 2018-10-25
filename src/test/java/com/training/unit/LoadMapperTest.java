package com.training.unit;

import com.training.entities.LoadEntity;
import com.training.entities.enums.LoadStatus;
import com.training.mappers.LoadMapper;
import com.training.models.Load;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;


public class LoadMapperTest {

    private Date date;

    @Before
    public void initDate(){
        date = new Date();
    }

    @Test
    public void getModelFromEntity(){
        Load load = new Load(1L, "Title","Description", date, 7, LoadStatus.NOT_ASSIGNED, null);
        LoadEntity loadEntity = new LoadEntity("Title", "Description", date, LoadStatus.NOT_ASSIGNED, 7, null);
        loadEntity.setId(1L);
        assertEquals(load, LoadMapper.mapEntityToModel(loadEntity));
    }

    @Test
    public void getModelFromEntityWithNotMatchingProperties(){
        Load load = new Load(1L, "Title","Description", date, 7, LoadStatus.NOT_ASSIGNED, null);
        LoadEntity loadEntity = new LoadEntity("Title1", "Description", date, LoadStatus.NOT_ASSIGNED, 7, null);
        loadEntity.setId(1L);
        assertNotEquals(load, LoadMapper.mapEntityToModel(loadEntity));
    }

    @Test
    public void getEntityFromModel(){
        Load load = new Load(1L, "Title","Description", date, 7, LoadStatus.NOT_ASSIGNED, null);
        LoadEntity loadEntity = new LoadEntity("Title", "Description", date, LoadStatus.NOT_ASSIGNED, 7, null);
        loadEntity.setId(1L);
        assertEquals(loadEntity, LoadMapper.mapModelToEntity(load));
    }

    @Test
    public void getEntityFromModelWithNotMatchingProperties(){
        Load load = new Load(1L, "Title","Description", date, 7, LoadStatus.NOT_ASSIGNED, null);
        LoadEntity loadEntity = new LoadEntity("Title1", "Description", date, LoadStatus.NOT_ASSIGNED, 7, null);
        loadEntity.setId(1L);
        assertNotEquals(loadEntity, LoadMapper.mapModelToEntity(load));
    }

    @Test
    public void getEntityFromNullModel(){
        assertNull(LoadMapper.mapModelToEntity(null));
    }

    @Test
    public void getModelFromNullEntity(){
        assertNull(LoadMapper.mapEntityToModel(null));
    }
}
