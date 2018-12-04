package com.training.services;

import com.training.LoadHelper;
import com.training.entities.LoadEntity;
import com.training.entities.VehicleEntity;
import com.training.mappers.LoadMapper;
import com.training.mappers.VehicleMapper;
import com.training.repositories.DriverRepository;
import com.training.repositories.LoadRepository;
import com.training.services.impl.LoadServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.validation.ValidationException;
import java.util.List;

import static com.training.DriverHelper.CO_DRIVER_LICENSE;
import static com.training.DriverHelper.PRIMARY_DRIVER_LICENSE;
import static com.training.LoadHelper.LOAD_ID;
import static com.training.LoadHelper.getLoad;
import static com.training.LoadHelper.getLoadAssignedToAnotherVehicle;
import static com.training.LoadHelper.getLoadAssignedToVehicle;
import static com.training.LoadHelper.getLoadForCreation;
import static com.training.LoadHelper.getLoadForCreationAssignedToVehicle;
import static com.training.LoadHelper.getLoadForUpdateWithNullProperty;
import static com.training.LoadHelper.getLoadList;
import static com.training.LoadHelper.getLoadModel;
import static com.training.LoadHelper.getLoadModelForCreation;
import static com.training.VehicleHelper.ANOTHER_VEHICLE_REGISTRATION_NUMBER;
import static com.training.VehicleHelper.VEHICLE_REGISTRATION_NUMBER;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LoadServiceTest {

    @Mock
    private LoadRepository loadRepository;

    @Mock
    private DriverRepository driverRepository;

    @Mock
    private VehicleService vehicleService;

    @Mock
    private LocationService locationService;

    private LoadService loadService;

    @Before
    public void init() {
        loadService = new LoadServiceImpl(loadRepository, driverRepository, vehicleService, locationService);
    }

    @Test
    public void findAllLoads() {
        List<LoadEntity> loads = getLoadList();
        when(loadRepository.findAll()).thenReturn(loads);

        assertEquals(LoadMapper.mapEntityListToModelList(loads), loadService.findAll());
        verify(loadRepository).findAll();
    }

    @Test
    public void findLoad() {
        LoadEntity loadEntity = getLoad();
        when(loadRepository.getOne(LOAD_ID)).thenReturn(loadEntity);

        assertEquals(getLoadModel(), loadService.find(LOAD_ID));
        verify(loadRepository).getOne(LOAD_ID);
    }

    @Test
    public void createLoad() {
        LoadEntity loadForCreation = getLoadForCreation();
        LoadEntity createdLoad = getLoad();
        when(loadRepository.saveAndFlush(loadForCreation)).thenReturn(createdLoad);

        loadService.save(getLoadModelForCreation());
        verify(loadRepository).saveAndFlush(loadForCreation);
    }

    @Test(expected = ValidationException.class)
    public void createLoadWithNullProperty() {
        LoadEntity loadEntity = LoadHelper.getLoadForCreationWithNullProperty();
        doThrow(new ValidationException()).when(loadRepository).saveAndFlush(loadEntity);

        loadService.save(LoadMapper.mapEntityToModel(loadEntity));
        verify(loadRepository).saveAndFlush(loadEntity);
    }

    @Test
    public void updateLoad() {
        LoadEntity loadForUpdate = getLoad();
        when(loadRepository.saveAndFlush(loadForUpdate)).thenReturn(loadForUpdate);

        loadService.save(getLoadModel());
        verify(loadRepository).saveAndFlush(loadForUpdate);
    }

    @Test(expected = ValidationException.class)
    public void updateLoadWithNullProperty() {
        LoadEntity loadForUpdate = getLoadForUpdateWithNullProperty();
        doThrow(new ValidationException()).when(loadRepository).saveAndFlush(loadForUpdate);

        loadService.save(LoadMapper.mapEntityToModel(loadForUpdate));
        verify(loadRepository).saveAndFlush(loadForUpdate);
    }

    @Test
    public void saveNewLoadAssignedToVehicle() {
        LoadEntity loadForCreationAssignedToVehicle = getLoadForCreationAssignedToVehicle();
        VehicleEntity vehicle = loadForCreationAssignedToVehicle.getVehicle();
        String primaryDriverLicense = PRIMARY_DRIVER_LICENSE;
        String coDriverLicense = CO_DRIVER_LICENSE;
        String registrationNumber = VEHICLE_REGISTRATION_NUMBER;
        Long pickUpLocationId = 1L;
        Long deliveryLocationId = 1L;

        when(vehicleService.findByRegistrationNumber(registrationNumber))
                .thenReturn(VehicleMapper.mapEntityToModel(vehicle));
        when(loadRepository.saveAndFlush(loadForCreationAssignedToVehicle)).thenReturn(getLoadAssignedToVehicle());
        doNothing().when(vehicleService).assignToDrivers(vehicle, primaryDriverLicense, coDriverLicense);

        loadService.saveAssignedLoad(getLoadModelForCreation(), registrationNumber,
                primaryDriverLicense, coDriverLicense, pickUpLocationId, deliveryLocationId);
        verify(vehicleService).findByRegistrationNumber(registrationNumber);
        verify(loadRepository).saveAndFlush(loadForCreationAssignedToVehicle);
        verify(vehicleService).assignToDrivers(vehicle, primaryDriverLicense, coDriverLicense);
    }

    @Test
    public void saveLoadAssignedToNewVehicle() {
        LoadEntity loadAssignedToVehicle = getLoadAssignedToVehicle();
        LoadEntity loadAssignedToAnotherVehicle = getLoadAssignedToAnotherVehicle();
        VehicleEntity currentVehicle = loadAssignedToVehicle.getVehicle();
        VehicleEntity newVehicle = loadAssignedToAnotherVehicle.getVehicle();
        String primaryDriverLicense = PRIMARY_DRIVER_LICENSE;
        String coDriverLicense = CO_DRIVER_LICENSE;
        String registrationNumber = ANOTHER_VEHICLE_REGISTRATION_NUMBER;
        Long pickUpLocationId = 1L;
        Long deliveryLocationId = 1L;

        when(loadRepository.getOne(LOAD_ID)).thenReturn(loadAssignedToVehicle);
        doNothing().when(vehicleService).freeVehicleAndDrivers(currentVehicle);
        when(vehicleService.findByRegistrationNumber(registrationNumber))
                .thenReturn(VehicleMapper.mapEntityToModel(newVehicle));
        when(loadRepository.saveAndFlush(loadAssignedToAnotherVehicle)).thenReturn(loadAssignedToAnotherVehicle);
        doNothing().when(vehicleService).assignToDrivers(newVehicle, primaryDriverLicense, coDriverLicense);

        loadService.saveAssignedLoad(LoadMapper.mapEntityToModel(loadAssignedToVehicle),
                registrationNumber, primaryDriverLicense, coDriverLicense, pickUpLocationId, deliveryLocationId);
        verify(loadRepository, times(2)).getOne(LOAD_ID);
        verify(vehicleService).findByRegistrationNumber(registrationNumber);
        verify(loadRepository).saveAndFlush(loadAssignedToAnotherVehicle);
        verify(vehicleService).assignToDrivers(newVehicle, primaryDriverLicense, coDriverLicense);
    }

    @Test
    public void deliverLoad() {
        LoadEntity loadEntity = getLoadAssignedToVehicle();
        VehicleEntity vehicleEntity = loadEntity.getVehicle();
        when(loadRepository.getOne(LOAD_ID)).thenReturn(loadEntity);
        doNothing().when(vehicleService).freeVehicleAndDrivers(vehicleEntity);
        doNothing().when(loadRepository).setDone(LOAD_ID);

        loadService.deliverLoad(LOAD_ID);
        verify(loadRepository).getOne(LOAD_ID);
        verify(vehicleService).freeVehicleAndDrivers(vehicleEntity);
        verify(loadRepository).setDone(LOAD_ID);
    }
}
