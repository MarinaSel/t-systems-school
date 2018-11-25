package com.training.repositories;

import com.training.entities.LoadEntity;
import com.training.test_config.TestWebConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ValidationException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.training.entities.enums.LoadStatus.NOT_ASSIGNED;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@Transactional
@ContextConfiguration(classes = TestWebConfig.class)
public class LoadRepositoryTest {

    @Autowired
    private LoadRepository loadRepository;

    private static final String TITLE = "Title";
    private static final String ANOTHER_TITLE = "Another title";

    @Test
    public void createAndFind() {
        LoadEntity loadEntity = getLoad(TITLE);
        loadRepository.saveAndFlush(loadEntity);

        assertEquals(Optional.of(loadEntity), loadRepository.findById(loadEntity.getId()));
    }

    @Test
    public void updateAndDelete() {
        LoadEntity loadEntity = getLoad(TITLE);
        loadRepository.saveAndFlush(loadEntity);
        Long id = loadEntity.getId();

        loadEntity.setTitle(ANOTHER_TITLE);
        loadRepository.saveAndFlush(loadEntity);
        assertEquals(Optional.of(loadEntity), loadRepository.findById(id));

        loadRepository.deleteById(id);
        assertEquals(Optional.empty(), loadRepository.findById(id));
    }

    @Test
    public void findAll() {
        LoadEntity loadEntity1 = getLoad(TITLE);
        LoadEntity loadEntity2 = getLoad(ANOTHER_TITLE);
        loadRepository.saveAndFlush(loadEntity1);
        loadRepository.saveAndFlush(loadEntity2);

        List<LoadEntity> expectedList = asList(loadEntity1, loadEntity2);
        List<LoadEntity> actualList = loadRepository.findAll();
        assertEquals(expectedList, actualList);
    }

    @Test(expected = ValidationException.class)
    public void createLoadWithNullProperties() {
        loadRepository.saveAndFlush(
                new LoadEntity(null, null, null, null, null, null, null));
    }

    private static LoadEntity getLoad(String title) {
        return new LoadEntity(null, title, "Description", new Date(), NOT_ASSIGNED, 7, null);
    }
}
