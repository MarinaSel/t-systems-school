package com.training.repositories;

import com.training.entities.DriverEntity;
import com.training.entities.enums.DriverStatus;
import com.training.test_config.TestWebConfig;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ValidationException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@Transactional
@ContextConfiguration(classes = TestWebConfig.class)
public class DriverRepositoryTest {

    @Autowired
    private DriverRepository driverRepository;

    private Date date;

    @Before
    public void initDate(){
        date = new Date();
    }

    @Test
    public void createAndFind(){
        DriverEntity newDriver = new DriverEntity(
                "licNaum", "Jane", "Doe", date, DriverStatus.FREE, null);
        DriverEntity expectedDriver = new DriverEntity(
                "licNaum", "Jane", "Doe", date, DriverStatus.FREE, null);

        driverRepository.saveAndFlush(newDriver);
        expectedDriver.setId(newDriver.getId());
        assertEquals(expectedDriver, driverRepository.findByDrivingLicenseNum("licNaum"));
    }

    @Test
    public void updateAndDelete(){
        DriverEntity newDriver = new DriverEntity(
                "licNaum", "Jane", "Doe", date, DriverStatus.FREE, null);
        DriverEntity expectedDriver = new DriverEntity(
                "licNaum", "Kate", "Doe", date, DriverStatus.FREE, null);
        driverRepository.saveAndFlush(newDriver);
        Long id = newDriver.getId();

        expectedDriver.setId(id);
        newDriver.setFirstName("Kate");
        driverRepository.saveAndFlush(newDriver);
        assertEquals(expectedDriver, newDriver);

        driverRepository.deleteById(id);
        assertEquals(Optional.empty(), driverRepository.findById(id));
    }
    @Test
    public void findAllByStatus(){
        DriverEntity driverEntity1 = new DriverEntity(
                "licNaum", "Kate", "Doe", date, DriverStatus.FREE, null);
        DriverEntity driverEntity2 = new DriverEntity(
                "licNaum1", "Jane", "Doe", date, DriverStatus.FREE, null);

        driverRepository.saveAndFlush(driverEntity1);
        driverRepository.saveAndFlush(driverEntity2);
        List<DriverEntity> driverEntities = driverRepository.findAllByStatus(DriverStatus.FREE);

        DriverEntity expectedDriver1 = new DriverEntity( "licNaum", "Kate", "Doe", date, DriverStatus.FREE, null);
        DriverEntity expectedDriver2 = new DriverEntity("licNaum1", "Jane", "Doe", date, DriverStatus.FREE, null);

        expectedDriver1.setId(driverEntity1.getId());
        expectedDriver2.setId(driverEntity2.getId());

        List<DriverEntity> expectedList = Arrays.asList(expectedDriver1, expectedDriver2);
        assertEquals(expectedList, driverEntities);
    }

    @Test(expected = ValidationException.class)
    public void createDriverWithNullProperties(){
        driverRepository.saveAndFlush(new DriverEntity(null, null, null,
                null, null, null));
    }
}
