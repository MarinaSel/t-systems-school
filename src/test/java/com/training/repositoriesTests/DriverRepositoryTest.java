import com.training.config.WebConfig;
import com.training.entities.DriverEntity;
import com.training.entities.enums.DriverStatus;
import com.training.repositories.DriverRepository;
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
@ContextConfiguration(classes = {WebConfig.class})
public class DriverRepositoryTest {

    @Autowired
    private DriverRepository driverRepository;

    private Date date;

    @Before
    public void initDate(){
        date = new Date();
    }

    @Test
    public void createAndUpdateAndDeleteAndFind(){
        DriverEntity newDriver = new DriverEntity(
                "licNaum", "Jane", "Doe", date, DriverStatus.FREE, null);
        DriverEntity expectedDriver = new DriverEntity(
                "licNaum", "Jane", "Doe", date, DriverStatus.FREE, null);
        expectedDriver.setId(1L);
        driverRepository.saveAndFlush(newDriver);
        assertEquals(expectedDriver, newDriver);

        DriverEntity expectedUpdatingDriver = new DriverEntity(
                "licNaum", "Kate", "Doe", date, DriverStatus.FREE, null);
        expectedUpdatingDriver.setId(1L);
        newDriver.setFirstName("Kate");
        assertEquals(expectedUpdatingDriver, newDriver);


        assertEquals(newDriver, driverRepository.findByDrivingLicenseNum("licNaum"));

        driverRepository.deleteById(newDriver.getId());
        assertEquals(Optional.empty(),driverRepository.findById(newDriver.getId()));
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

        expectedDriver1.setId(1L);
        expectedDriver2.setId(2L);

        List<DriverEntity> expectedList = Arrays.asList(expectedDriver1, expectedDriver2);
        assertEquals(expectedList, driverEntities);
    }
}
