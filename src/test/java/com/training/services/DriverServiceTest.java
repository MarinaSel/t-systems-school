package com.training.services;

import com.training.entities.DriverEntity;
import com.training.entities.UserEntity;
import com.training.mappers.DriverMapper;
import com.training.mappers.UserMapper;
import com.training.repositories.DriverRepository;
import com.training.repositories.UserRepository;
import com.training.services.impl.DriverServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.validation.ValidationException;
import java.util.List;

import static com.training.DriverHelper.PRIMARY_DRIVER_ID;
import static com.training.DriverHelper.PRIMARY_DRIVER_LICENSE;
import static com.training.DriverHelper.getDriverForCreation;
import static com.training.DriverHelper.getDriverForCreationWithNullProperty;
import static com.training.DriverHelper.getDriverForUpdateWithNullProperty;
import static com.training.DriverHelper.getDriverList;
import static com.training.DriverHelper.getDriverModel;
import static com.training.DriverHelper.getDriverModelForCreation;
import static com.training.DriverHelper.getDriverWithUser;
import static com.training.DriverHelper.getPrimaryDriver;
import static com.training.UserHelper.getUser;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DriverServiceTest {

    @Mock
    private DriverRepository driverRepository;

    @Mock
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    private DriverService driverService;

    @Before
    public void init() {
        driverService = new DriverServiceImpl(driverRepository, userService, userRepository);
    }

    @Test
    public void findAllDrivers() {
        List<DriverEntity> drivers = getDriverList();
        when(driverRepository.findAll()).thenReturn(drivers);

        assertEquals(DriverMapper.mapEntityListToModelList(drivers), driverService.findAll());
        verify(driverRepository).findAll();
    }

    @Test
    public void findDriver() {
        DriverEntity driverEntity = getPrimaryDriver();
        when(driverRepository.getOne(PRIMARY_DRIVER_ID)).thenReturn(driverEntity);

        assertEquals(getDriverModel(), driverService.find(PRIMARY_DRIVER_ID));
        verify(driverRepository).getOne(PRIMARY_DRIVER_ID);
    }

    @Test
    public void createDriver() {
        DriverEntity driverForCreation = getDriverForCreation();
        DriverEntity createdDriver = getPrimaryDriver();
        when(driverRepository.saveAndFlush(driverForCreation)).thenReturn(createdDriver);

        driverService.save(getDriverModelForCreation());
        verify(driverRepository).saveAndFlush(driverForCreation);
    }

    @Test(expected = ValidationException.class)
    public void createDriverWithNullProperty() {
        DriverEntity driverEntity = getDriverForCreationWithNullProperty();
        doThrow(new ValidationException()).when(driverRepository).saveAndFlush(driverEntity);

        driverService.save(DriverMapper.mapEntityToModel(driverEntity));
        verify(driverRepository).saveAndFlush(driverEntity);
    }

    @Test
    public void updateDriver() {
        DriverEntity driverForUpdate = getPrimaryDriver();
        when(driverRepository.saveAndFlush(driverForUpdate)).thenReturn(driverForUpdate);

        driverService.save(getDriverModel());
        verify(driverRepository).saveAndFlush(driverForUpdate);
    }

    @Test(expected = ValidationException.class)
    public void updateDriverWithNullProperty() {
        DriverEntity driverForUpdate = getDriverForUpdateWithNullProperty();
        doThrow(new ValidationException()).when(driverRepository).saveAndFlush(driverForUpdate);

        driverService.save(DriverMapper.mapEntityToModel(driverForUpdate));
        verify(driverRepository).saveAndFlush(driverForUpdate);
    }

    @Test
    public void findByDrivingLicenseNum() {
        DriverEntity driverEntity = getPrimaryDriver();
        when(driverRepository.findByDrivingLicenseNum(PRIMARY_DRIVER_LICENSE)).thenReturn(driverEntity);

        assertEquals(getDriverModel(), driverService.findByDrivingLicenseNum(PRIMARY_DRIVER_LICENSE));
        verify(driverRepository).findByDrivingLicenseNum(PRIMARY_DRIVER_LICENSE);
    }


    @Test
    public void findByUser() {
        DriverEntity driverEntity = getDriverWithUser();
        UserEntity userEntity = getUser();
        when(driverRepository.findByUser(userEntity)).thenReturn(driverEntity);

        assertEquals(getDriverWithUser(), DriverMapper.mapModelToEntity(driverService.findByUser(
                UserMapper.mapEntityToModel(userEntity))));
        verify(driverRepository).findByUser(userEntity);
    }
}
