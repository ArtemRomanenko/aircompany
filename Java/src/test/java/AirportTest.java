import models.ClassificationLevel;
import models.MilitaryType;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import planes.ExperimentalPlane;
import planes.MilitaryPlane;
import planes.PassengerPlane;
import planes.Plane;

import java.util.List;

public class AirportTest {

    private static PassengerPlane maxPassengerCapacityPlane = new PassengerPlane("Boeing-747", 980,
            16100, 70500, 242);
    private Airport airport;

    @BeforeTest
    public void airportInitialization() {
        airport = new Airport(Runner.planes);
    }

    @Test
    public void getTransportMilitaryPlanes() {
        List<MilitaryPlane> transportMilitaryPlanes =
                airport.getMilitaryPlanesType(MilitaryType.TRANSPORT);
        for (MilitaryPlane militaryPlane : transportMilitaryPlanes) {
            Assert.assertEquals(militaryPlane.getType(), MilitaryType.TRANSPORT);
        }
    }

    @Test
    public void getPlaneWithMaxPassengerCapacity() {
        PassengerPlane passengerPlaneWithMaxCapacity = airport.getPlaneWithMaxPassengersCapacity();
        Assert.assertEquals(maxPassengerCapacityPlane, passengerPlaneWithMaxCapacity);
    }

    @Test
    public void getPlaneWithMaxLoadCapacity() {
        airport.sortByMaxLoadCapacity();
        List<? extends Plane> planesSortedByMaxLoadCapacity = airport.getPlanes();

        boolean nextPlaneMaxLoadCapacityIsHigherThanCurrent = true;
        for (int i = 0; i < planesSortedByMaxLoadCapacity.size() - 1; i++) {
            Plane currentPlane = planesSortedByMaxLoadCapacity.get(i);
            Plane nextPlane = planesSortedByMaxLoadCapacity.get(i + 1);
            if (currentPlane.getMaxLoadCapacity() > nextPlane.getMaxLoadCapacity()) {
                nextPlaneMaxLoadCapacityIsHigherThanCurrent = false;
                break;
            }
        }
        Assert.assertTrue(nextPlaneMaxLoadCapacityIsHigherThanCurrent);
    }

    @Test
    public void isPlaneABomber() {
        List<MilitaryPlane> bomberMilitaryPlanes = airport.getMilitaryPlanesType(MilitaryType.BOMBER);
        for (MilitaryPlane militaryPlane : bomberMilitaryPlanes) {
            Assert.assertEquals(militaryPlane.getType(), MilitaryType.BOMBER);
        }
    }

    @Test
    public void isPlaneClassified() {
        List<ExperimentalPlane> experimentalPlanes
                = (List<ExperimentalPlane>) airport.getPlaneByType(ExperimentalPlane.class);
        for (ExperimentalPlane experimentalPlane : experimentalPlanes) {
            Assert.assertNotEquals(experimentalPlane.getClassificationLevel(), ClassificationLevel.UNCLASSIFIED);
        }
    }
}
