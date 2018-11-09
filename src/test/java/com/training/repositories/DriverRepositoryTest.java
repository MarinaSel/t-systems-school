package com.training.repositories;

import com.training.entities.DriverEntity;
import com.training.entities.UserEntity;
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

import static com.training.entities.enums.DriverStatus.FREE;
import static java.util.Arrays.asList;
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

    private static final String LICENSE_NUMBER = "License";
    private static final String ANOTHER_LICENSE_NUMBER = "Another";

    @Test
    public void createAndFind() {
        UserEntity userEntity = getUser();
        userRepository.saveAndFlush(userEntity);
        DriverEntity driverEntity = getDriver(LICENSE_NUMBER, userEntity);
        driverRepository.saveAndFlush(driverEntity);

        assertEquals(driverEntity, driverRepository.findByDrivingLicenseNum(LICENSE_NUMBER));
    }

    @Test
    public void updateAndDelete() {
        UserEntity userEntity = getUser();
        userRepository.saveAndFlush(userEntity);
        DriverEntity driverEntity = getDriver(LICENSE_NUMBER, userEntity);
        driverRepository.saveAndFlush(driverEntity);
        Long id = driverEntity.getId();

        driverEntity.setDrivingLicenseNum(ANOTHER_LICENSE_NUMBER);
        driverRepository.saveAndFlush(driverEntity);
        assertEquals(Optional.of(driverEntity), driverRepository.findById(id));

        driverRepository.deleteById(id);
        assertEquals(Optional.empty(), driverRepository.findById(id));
    }

    @Test
    public void findAllByStatus() {
        UserEntity userEntity = getUser();
        userRepository.saveAndFlush(userEntity);
        DriverEntity driverEntity1 = getDriver(LICENSE_NUMBER, userEntity);
        DriverEntity driverEntity2 = getDriver(ANOTHER_LICENSE_NUMBER, userEntity);
        driverRepository.saveAndFlush(driverEntity1);
        driverRepository.saveAndFlush(driverEntity2);

        List<DriverEntity> expectedList = asList(driverEntity1, driverEntity2);
        List<DriverEntity> actualList = driverRepository.findAllByStatus(FREE);
        assertEquals(expectedList, actualList);
    }

    @Test(expected = ValidationException.class)
    public void createDriverWithNullProperties() {
        driverRepository.saveAndFlush(
                new DriverEntity(null, null, null, null, null, null));
    }

    private static UserEntity getUser() {
        return new UserEntity(null, "first", "last", "login", "password");
    }

    private static DriverEntity getDriver(String licenseNum, UserEntity userEntity) {
        return new DriverEntity(null, licenseNum, new Date(), FREE, new Date(), userEntity);
    }
}
