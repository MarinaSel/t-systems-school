package com.training.repositories;

import com.training.entities.VehicleEntity;
import com.training.entities.enums.VehicleStatus;
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
public class VehicleRepositoryTest {

    @Autowired
    private VehicleRepository vehicleRepository;

    private Date date;

    @Before
    public void init(){
        date = new Date();
    }
    @Test
    public void createAndFind(){
        VehicleEntity newVehicle = new VehicleEntity("model", date, "RegNumb", 8, VehicleStatus.FREE);
        VehicleEntity expectedVehicle = new VehicleEntity("model", date, "RegNumb", 8, VehicleStatus.FREE);

        vehicleRepository.saveAndFlush(newVehicle);
        expectedVehicle.setId(newVehicle.getId());

        assertEquals(expectedVehicle, vehicleRepository.findVehicleEntityByRegistrationNumber("RegNumb"));
    }

    @Test
    public void updateAndDelete(){
        VehicleEntity newVehicle = new VehicleEntity("model", date, "RegNumb", 8, VehicleStatus.FREE);
        VehicleEntity expectedVehicle = new VehicleEntity("model", date, "RegNum1", 8, VehicleStatus.FREE);

        vehicleRepository.saveAndFlush(newVehicle);
        Long id = newVehicle.getId();
        expectedVehicle.setId(id);
        newVehicle.setRegistrationNumber("RegNum1");
        assertEquals(expectedVehicle, newVehicle);

        vehicleRepository.deleteById(id);
        assertEquals(Optional.empty(), vehicleRepository.findById(id));
    }

    @Test
    public void findAllByStatus(){
        VehicleEntity vehicleEntity1 = new VehicleEntity("model", date, "RegNum1", 8, VehicleStatus.FREE);
        VehicleEntity vehicleEntity2 = new VehicleEntity("model", date, "RegNum2", 8, VehicleStatus.FREE);
        vehicleRepository.saveAndFlush(vehicleEntity1);
        vehicleRepository.saveAndFlush(vehicleEntity2);

        List<VehicleEntity> vehicles = vehicleRepository.findAllByStatus(VehicleStatus.FREE);

        VehicleEntity expectedVehicle1 = new VehicleEntity("model", date, "RegNum1", 8, VehicleStatus.FREE);
        VehicleEntity expectedVehicle2 = new VehicleEntity("model", date, "RegNum2", 8, VehicleStatus.FREE);
        expectedVehicle1.setId(vehicleEntity1.getId());
        expectedVehicle2.setId(vehicleEntity2.getId());

        List<VehicleEntity> expectedVehicles = Arrays.asList(expectedVehicle1, expectedVehicle2);

        assertEquals(expectedVehicles, vehicles);
    }

    @Test(expected = ValidationException.class)
    public void createVehicleWithIllegalRegistrationNumber(){
        vehicleRepository.saveAndFlush(new VehicleEntity("hahhh", date, "22sdsssdsd", 10, null));
    }
}
