package com.training.repositories;

import com.training.entities.VehicleEntity;
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

import static com.training.entities.enums.VehicleStatus.FREE;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@Transactional
@ContextConfiguration(classes = TestWebConfig.class)
public class VehicleRepositoryTest {

    @Autowired
    private VehicleRepository vehicleRepository;

    private static final String REG_NUMBER = "Reg_Num";
    private static final String ANOTHER_REG_NUMBER = "Another";

    @Test
    public void createAndFind() {
        VehicleEntity newVehicle = getVehicle(REG_NUMBER);
        vehicleRepository.saveAndFlush(newVehicle);

        assertEquals(newVehicle, vehicleRepository.findVehicleEntityByRegistrationNumber(REG_NUMBER));
    }

    @Test
    public void updateAndDelete() {
        VehicleEntity newVehicle = getVehicle(REG_NUMBER);
        vehicleRepository.saveAndFlush(newVehicle);
        Long id = newVehicle.getId();

        newVehicle.setModel(ANOTHER_REG_NUMBER);
        vehicleRepository.saveAndFlush(newVehicle);
        assertEquals(Optional.of(newVehicle), vehicleRepository.findById(id));

        vehicleRepository.deleteById(id);
        assertEquals(Optional.empty(), vehicleRepository.findById(id));
    }

    @Test
    public void findAllByStatus() {
        VehicleEntity vehicleEntity1 = getVehicle(REG_NUMBER);
        VehicleEntity vehicleEntity2 = getVehicle(ANOTHER_REG_NUMBER);
        vehicleRepository.saveAndFlush(vehicleEntity1);
        vehicleRepository.saveAndFlush(vehicleEntity2);

        List<VehicleEntity> expectedList = asList(vehicleEntity1, vehicleEntity2);
        List<VehicleEntity> actualList = vehicleRepository.findAllByStatus(FREE);
        assertEquals(expectedList, actualList);
    }

    @Test(expected = ValidationException.class)
    public void createVehicleWithNullProperties() {
        vehicleRepository.saveAndFlush(
                new VehicleEntity(null, null, null, null, null, null));
    }

    private static VehicleEntity getVehicle(String regNumber) {
        return new VehicleEntity(null, "Model", new Date(), regNumber, 12000, FREE);
    }
}
