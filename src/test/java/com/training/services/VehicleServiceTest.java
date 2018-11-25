package com.training.services;

import com.training.VehicleHelper;
import com.training.entities.DriverEntity;
import com.training.entities.VehicleEntity;
import com.training.mappers.DriverMapper;
import com.training.mappers.VehicleMapper;
import com.training.repositories.DriverRepository;
import com.training.repositories.LoadRepository;
import com.training.repositories.VehicleRepository;
import com.training.services.impl.VehicleServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.validation.ValidationException;
import java.util.List;

import static com.training.DriverHelper.CO_DRIVER_ID;
import static com.training.DriverHelper.CO_DRIVER_LICENSE;
import static com.training.DriverHelper.PRIMARY_DRIVER_ID;
import static com.training.DriverHelper.PRIMARY_DRIVER_LICENSE;
import static com.training.DriverHelper.getCoDriver;
import static com.training.DriverHelper.getDriverWithUser;
import static com.training.DriverHelper.getPrimaryDriver;
import static com.training.VehicleHelper.CAPACITY;
import static com.training.VehicleHelper.VEHICLE_ID;
import static com.training.VehicleHelper.VEHICLE_REGISTRATION_NUMBER;
import static com.training.VehicleHelper.getVehicle;
import static com.training.VehicleHelper.getVehicleForCreation;
import static com.training.VehicleHelper.getVehicleForCreationWithNullProperty;
import static com.training.VehicleHelper.getVehicleForUpdateWithNullProperty;
import static com.training.VehicleHelper.getVehicleList;
import static com.training.VehicleHelper.getVehicleModel;
import static com.training.VehicleHelper.getVehicleModelForCreation;
import static com.training.VehicleHelper.getVehicleWithDrivers;
import static com.training.VehicleHelper.getVehicleWithLoadsAndDrivers;
import static com.training.entities.enums.VehicleStatus.FREE;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class VehicleServiceTest {

    @Mock
    private VehicleRepository vehicleRepository;

    @Mock
    private DriverRepository driverRepository;

    @Mock
    private LoadRepository loadRepository;

    @Mock
    private DriverService driverService;

    private VehicleService vehicleService;

    @Before
    public void init() {
        vehicleService = new VehicleServiceImpl(vehicleRepository, driverRepository, loadRepository, driverService);
    }

    @Test
    public void findAllVehicles() {
        List<VehicleEntity> vehicles = getVehicleList();
        when(vehicleRepository.findAll()).thenReturn(vehicles);

        assertEquals(VehicleMapper.mapEntityListToModelList(vehicles), vehicleService.findAll());
        verify(vehicleRepository).findAll();
    }

    @Test
    public void findVehicle() {
        VehicleEntity vehicleEntity = getVehicle();
        when(vehicleRepository.getOne(VEHICLE_ID)).thenReturn(vehicleEntity);

        assertEquals(getVehicleModel(), vehicleService.find(VEHICLE_ID));
        verify(vehicleRepository).getOne(VEHICLE_ID);
    }

    @Test
    public void createVehicle() {
        VehicleEntity vehicleForCreation = getVehicleForCreation();
        VehicleEntity createdVehicle = getVehicle();
        when(vehicleRepository.saveAndFlush(vehicleForCreation)).thenReturn(createdVehicle);

        vehicleService.save(getVehicleModelForCreation());
        verify(vehicleRepository).saveAndFlush(vehicleForCreation);
    }

    @Test(expected = ValidationException.class)
    public void createVehicleWithNullProperty() {
        VehicleEntity vehicleEntity = getVehicleForCreationWithNullProperty();
        doThrow(new ValidationException()).when(vehicleRepository).saveAndFlush(vehicleEntity);

        vehicleService.save(VehicleMapper.mapEntityToModel(vehicleEntity));
        verify(vehicleRepository).saveAndFlush(vehicleEntity);
    }

    @Test
    public void updateVehicle() {
        VehicleEntity vehicleForUpdate = getVehicle();
        when(vehicleRepository.saveAndFlush(vehicleForUpdate)).thenReturn(vehicleForUpdate);

        vehicleService.save(getVehicleModel());
        verify(vehicleRepository).saveAndFlush(vehicleForUpdate);
    }

    @Test(expected = ValidationException.class)
    public void updateVehicleWithNullProperty() {
        VehicleEntity vehicleForUpdate = getVehicleForUpdateWithNullProperty();
        doThrow(new ValidationException()).when(vehicleRepository).saveAndFlush(vehicleForUpdate);

        vehicleService.save(VehicleMapper.mapEntityToModel(vehicleForUpdate));
        verify(vehicleRepository).saveAndFlush(vehicleForUpdate);
    }

    @Test
    public void findByRegistrationNumber() {
        VehicleEntity vehicleEntity = getVehicle();
        when(vehicleRepository.findVehicleEntityByRegistrationNumber(VEHICLE_REGISTRATION_NUMBER))
                .thenReturn(vehicleEntity);

        assertEquals(getVehicleModel(), vehicleService.findByRegistrationNumber(VEHICLE_REGISTRATION_NUMBER));
        verify(vehicleRepository).findVehicleEntityByRegistrationNumber(VEHICLE_REGISTRATION_NUMBER);
    }

    @Test
    public void findFreeVehicles() {
        List<VehicleEntity> vehicles = getVehicleList();
        when(vehicleRepository.findAllByStatus(FREE)).thenReturn(vehicles);

        assertEquals(VehicleMapper.mapEntityListToModelList(vehicles),
                vehicleService.findAllFreeWithNecessaryCapacity(CAPACITY));
        verify(vehicleRepository).findAllByStatus(FREE);
    }

    @Test
    public void checkIfVehicleIsEmpty() {
        VehicleEntity vehicleEntity = getVehicleWithDrivers();
        doNothing().when(driverRepository).setFree(PRIMARY_DRIVER_ID, CO_DRIVER_ID);
        doNothing().when(vehicleRepository).setFree(VEHICLE_ID);

        vehicleService.freeVehicleAndDrivers(vehicleEntity);
        verify(driverRepository).setFree(PRIMARY_DRIVER_ID, CO_DRIVER_ID);
        verify(vehicleRepository).setFree(VEHICLE_ID);
    }

    @Test
    public void startDelivery() {
        VehicleEntity vehicleEntity = getVehicleWithLoadsAndDrivers();
        when(vehicleRepository.getOne(VEHICLE_ID)).thenReturn(vehicleEntity);
        doNothing().when(driverRepository).setWorking(PRIMARY_DRIVER_ID, CO_DRIVER_ID);
        doNothing().when(vehicleRepository).setWorking(VEHICLE_ID);
        doNothing().when(loadRepository).setInProgress(vehicleEntity);

        vehicleService.startDelivery(VEHICLE_ID);
        verify(vehicleRepository).getOne(VEHICLE_ID);
        verify(driverRepository).setWorking(PRIMARY_DRIVER_ID, CO_DRIVER_ID);
        verify(vehicleRepository).setWorking(VEHICLE_ID);
        verify(loadRepository).setInProgress(vehicleEntity);
    }

    @Test
    public void assignToDriver() {
        DriverEntity primaryDriver = getPrimaryDriver();
        DriverEntity coDriver = getCoDriver();
        VehicleEntity vehicleEntity = getVehicleWithDrivers();

        when(driverRepository.findByDrivingLicenseNum(PRIMARY_DRIVER_LICENSE)).thenReturn(primaryDriver);
        when(driverRepository.findByDrivingLicenseNum(CO_DRIVER_LICENSE)).thenReturn(coDriver);
        when(vehicleRepository.saveAndFlush(vehicleEntity)).thenReturn(vehicleEntity);

        vehicleService.assignToDrivers(vehicleEntity, PRIMARY_DRIVER_LICENSE,
                CO_DRIVER_LICENSE);
        verify(driverRepository).findByDrivingLicenseNum(PRIMARY_DRIVER_LICENSE);
        verify(driverRepository).findByDrivingLicenseNum(CO_DRIVER_LICENSE);
        verify(vehicleRepository).saveAndFlush(vehicleEntity);
    }

    @Test
    public void getVehicleOfAuthenticatedDriver() {
        DriverEntity driverEntity = getDriverWithUser();
        VehicleEntity vehicleEntity = VehicleHelper.getVehicleWithDriverWithUser();
        when(driverService.getAuthenticatedDriver()).thenReturn(DriverMapper.mapEntityToModel(driverEntity));
        when(vehicleRepository.findVehicleByDriver(driverEntity)).thenReturn(vehicleEntity);

        assertEquals(VehicleMapper.mapEntityToModel(vehicleEntity), vehicleService.getVehicleOfAuthenticatedDriver());
        verify(driverService).getAuthenticatedDriver();
        verify(vehicleRepository).findVehicleByDriver(driverEntity);
    }
}
