package java.com.training.unitTests;

import com.training.entities.VehicleEntity;
import com.training.entities.enums.VehicleStatus;
import com.training.mappers.VehicleMapper;
import com.training.models.Vehicle;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;


public class VehicleMapperTest {

    @Test
    public void getModelFromEntity(){
        Vehicle vehicle = new Vehicle(1L, "564791j", 7, VehicleStatus.FREE);
        VehicleEntity vehicleEntity = new VehicleEntity("564791j", 7, VehicleStatus.FREE);
        vehicleEntity.setId(1L);
        assertEquals(vehicle, VehicleMapper.getModelFromEntity(vehicleEntity));

    }

    @Test
    public void getModelFromEntityWithNotMatchingProperties(){
        Vehicle vehicle = new Vehicle(1L, "564791j", 7, VehicleStatus.FREE);
        VehicleEntity vehicleEntity = new VehicleEntity("1258977", 7, VehicleStatus.FREE);
        vehicleEntity.setId(1L);
        assertNotEquals(vehicle, VehicleMapper.getModelFromEntity(vehicleEntity));
    }

    @Test
    public void getEntityFromModel(){
        Vehicle vehicle = new Vehicle(1L, "564791j", 7, VehicleStatus.FREE);
        VehicleEntity vehicleEntity = new VehicleEntity("564791j", 7, VehicleStatus.FREE);
        vehicleEntity.setId(1L);
        assertEquals(vehicleEntity, VehicleMapper.getEntityFromModel(vehicle));
    }

    @Test
    public void getEntityFromModelWithNotMatchingProperties(){
        Vehicle vehicle = new Vehicle(1L, "564791j", 7, VehicleStatus.FREE);
        VehicleEntity vehicleEntity = new VehicleEntity("1111111", 7, VehicleStatus.FREE);
        vehicleEntity.setId(1L);
        assertNotEquals(vehicleEntity, VehicleMapper.getEntityFromModel(vehicle));
    }

    @Test
    public void getEntityFromNullModel(){
        assertNull(VehicleMapper.getEntityFromModel(null));
    }

    @Test
    public void getModelFromNullEntity(){
        assertNull(VehicleMapper.getModelFromEntity(null));
    }
}
