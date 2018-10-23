package com.training.repositories;

import com.training.entities.LoadEntity;
import com.training.entities.enums.LoadStatus;
import com.training.test_config.TestWebConfig;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@Transactional
@ContextConfiguration(classes = TestWebConfig.class)
public class LoadRepositoryTest {

    @Autowired
    private LoadRepository loadRepository;

    private Date date;

    @Before
    public void initDate(){
        date = new Date();
    }

    @Test
    public void createAndFind(){
        LoadEntity newLoad = new LoadEntity(
                "Title", "Description", date,LoadStatus.NOT_ASSIGNED, 7, null);
        LoadEntity expectedLoad = new LoadEntity(
                "Title", "Description", date,LoadStatus.NOT_ASSIGNED, 7, null);
        loadRepository.saveAndFlush(newLoad);
        expectedLoad.setId(newLoad.getId());

        assertEquals(expectedLoad, loadRepository.getOne(newLoad.getId()));

    }

    @Test
    public void updateAndDelete(){
        LoadEntity newLoad = new LoadEntity(
                "Title", "Description", date,LoadStatus.NOT_ASSIGNED, 7, null);
        LoadEntity expectedLoad = new LoadEntity(
                "Title", "Description", date,LoadStatus.NOT_ASSIGNED, 22, null);
        loadRepository.saveAndFlush(newLoad);
        Long id = newLoad.getId();

        expectedLoad.setId(id);
        newLoad.setWeight(22);
        loadRepository.saveAndFlush(newLoad);
        assertEquals(expectedLoad, newLoad);

        loadRepository.deleteById(newLoad.getId());
        assertEquals(Optional.empty(), loadRepository.findById(id));
    }

    @Test
    public void findAll(){
        LoadEntity loadEntity1 = new LoadEntity(
                "Title", "Description", date,LoadStatus.NOT_ASSIGNED, 7, null);
        LoadEntity loadEntity2 = new LoadEntity(
                "Title1", "Description", date,LoadStatus.NOT_ASSIGNED, 7, null);

        loadRepository.saveAndFlush(loadEntity1);
        loadRepository.saveAndFlush(loadEntity2);

        List<LoadEntity> loads = loadRepository.findAll();
        LoadEntity expectedLoad1 = new LoadEntity(
                "Title", "Description", date,LoadStatus.NOT_ASSIGNED, 7, null);
        LoadEntity expectedLoad2 = new LoadEntity(
                "Title1", "Description", date,LoadStatus.NOT_ASSIGNED, 7, null);

        expectedLoad1.setId(loadEntity1.getId());
        expectedLoad2.setId(loadEntity2.getId());

        List<LoadEntity> expectedList = Arrays.asList(expectedLoad1, expectedLoad2);
        assertEquals(expectedList, loads);
    }
}
