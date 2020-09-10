import models.MilitaryType;
import planes.MilitaryPlane;
import planes.PassengerPlane;
import planes.Plane;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Airport {
    private List<? extends Plane> planes;

    public Airport(List<? extends Plane> planes) {
        this.planes = planes;
    }


    public List<? extends Plane> getPlaneByType(Class<? extends Plane> requestedPlane) {
        List<Plane> sortedPlanes = new ArrayList<>();
        for (Plane plane : planes) {
            if (plane.getClass().isAssignableFrom(requestedPlane)) {
                sortedPlanes.add(plane);
            }
        }
        return sortedPlanes;
    }

    public PassengerPlane getPlaneWithMaxPassengersCapacity() {
        List<PassengerPlane> passengerPlanes = (List<PassengerPlane>) getPlaneByType(PassengerPlane.class);
        PassengerPlane planeWithMaxCapacity = passengerPlanes.get(0);
        for (PassengerPlane passengerPlane : passengerPlanes) {
            if (passengerPlane.getPassengersCapacity() > planeWithMaxCapacity.getPassengersCapacity()) {
                planeWithMaxCapacity = passengerPlane;
            }
        }
        return planeWithMaxCapacity;
    }

    public List<MilitaryPlane> getMilitaryPlanesType(MilitaryType militaryType) {
        List<MilitaryPlane> MilitaryPlanesType = new ArrayList<>();
        List<MilitaryPlane> militaryPlanes = (List<MilitaryPlane>) getPlaneByType(MilitaryPlane.class);
        for (MilitaryPlane plane : militaryPlanes) {
            if (plane.getType() == militaryType) {
                MilitaryPlanesType.add(plane);
            }
        }
        return MilitaryPlanesType;
    }

    public Airport sortByMaxDistance() {
        planes.sort(new Comparator<Plane>() {
            public int compare(Plane o1, Plane o2) {
                return o1.getMaxFlightDistance() - o2.getMaxFlightDistance();
            }
        });
        return this;
    }

    public Airport sortByMaxSpeed() {
        Collections.sort(planes, new Comparator<Plane>() {
            public int compare(Plane o1, Plane o2) {
                return o1.getMaxSpeed() - o2.getMaxSpeed();
            }
        });
        return this;
    }

    public Airport sortByMaxLoadCapacity() {
        planes.sort(new Comparator<Plane>() {
            public int compare(Plane o1, Plane o2) {
                return o1.getMaxLoadCapacity() - o2.getMaxLoadCapacity();
            }
        });
        return this;
    }

    public List<? extends Plane> getPlanes() {
        return planes;
    }

    @Override
    public String toString() {
        return String.format("Airport planes = %s", planes.toString());

    }

}
