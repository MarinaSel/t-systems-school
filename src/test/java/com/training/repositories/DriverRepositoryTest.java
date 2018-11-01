package com.training.repositories;

import com.training.entities.DriverEntity;
import com.training.entities.UserEntity;
import com.training.entities.enums.DriverStatus;
import com.training.test_config.TestWebConfig;
import org.junit.BeforeClass;
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

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@Transactional
@ContextConfiguration(classes = TestWebConfig.class)
public class DriverRepositoryTest {

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private UserRepository userRepository;

    private static Date date;
    private static UserEntity userEntity;

    @BeforeClass
    public static void init() {
        userEntity = new UserEntity("first", "last", "login", "password");
        date = new Date();
    }

    @Test
    public void createAndFind(){
        userRepository.saveAndFlush(userEntity);
        DriverEntity newDriver = new DriverEntity(
                "licNumber", date, DriverStatus.FREE, null, userEntity);
        DriverEntity expectedDriver = new DriverEntity(
                "licNumber", date, DriverStatus.FREE, null, userEntity);

        driverRepository.saveAndFlush(newDriver);
        expectedDriver.setId(newDriver.getId());
        assertEquals(expectedDriver, driverRepository.findByDrivingLicenseNum("licNumber"));
    }

    @Test
    public void updateAndDelete(){
        userRepository.saveAndFlush(userEntity);
        DriverEntity newDriver = new DriverEntity(
                "licNum", date, DriverStatus.FREE, null, userEntity);
        DriverEntity expectedDriver = new DriverEntity(
                "licNum1", date, DriverStatus.FREE, null, userEntity);
        driverRepository.saveAndFlush(newDriver);
        Long id = newDriver.getId();

        expectedDriver.setId(id);
        newDriver.setDrivingLicenseNum("licNum1");
        driverRepository.saveAndFlush(newDriver);
        assertEquals(expectedDriver, newDriver);

        driverRepository.deleteById(id);
        assertEquals(Optional.empty(), driverRepository.findById(id));
    }
    @Test
    public void findAllByStatus(){
        userRepository.saveAndFlush(userEntity);
        DriverEntity driverEntity1 = new DriverEntity(
                "licNumber", date, DriverStatus.FREE, null, userEntity);
        DriverEntity driverEntity2 = new DriverEntity(
                "licNumber1", date, DriverStatus.FREE, null, userEntity);

        driverRepository.saveAndFlush(driverEntity1);
        driverRepository.saveAndFlush(driverEntity2);
        List<DriverEntity> driverEntities = driverRepository.findAllByStatus(DriverStatus.FREE);

        DriverEntity expectedDriver1 = new DriverEntity("licNumber", date, DriverStatus.FREE, null, userEntity);
        DriverEntity expectedDriver2 = new DriverEntity("licNumber1", date, DriverStatus.FREE, null, userEntity);

        expectedDriver1.setId(driverEntity1.getId());
        expectedDriver2.setId(driverEntity2.getId());

        List<DriverEntity> expectedList = Arrays.asList(expectedDriver1, expectedDriver2);
        assertEquals(expectedList, driverEntities);
    }

    @Test(expected = ValidationException.class)
    public void createDriverWithNullProperties(){
        driverRepository.saveAndFlush(new DriverEntity(null, null, null, null, null));
    }

}
