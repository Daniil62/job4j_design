package ru.job4j.chapter1.ood.lsp.parking;

import org.junit.Ignore;
import org.junit.Test;
import ru.job4j.chapter1.ood.lsp.parking.model.Car;
import ru.job4j.chapter1.ood.lsp.parking.model.Parking;
import ru.job4j.chapter1.ood.lsp.parking.model.Truck;
import ru.job4j.chapter1.ood.lsp.parking.model.Vehicle;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ParkingTest {

    @Ignore
    @Test
    public void whenClearPlaceFromCar() {
        BaseParking parking = new Parking(1, 1);
        Vehicle car = new Car("some car", "O000OO");
        assertThat(parking.getFreeCarPlacesCount(), is(1));
        assertTrue(parking.takePlace(car));
        assertThat(parking.getFreeCarPlacesCount(), is(0));
        assertTrue(parking.clearPlace(car));
        assertThat(parking.getFreeCarPlacesCount(), is(1));
    }

    @Ignore
    @Test
    public void whenClearTruckPlace() {
        BaseParking parking = new Parking(1, 1);
        Vehicle truck = new Truck(2, "some truck", "O000OO");
        assertThat(parking.getFreeTruckPlacesCount(), is(1));
        assertTrue(parking.takePlace(truck));
        assertThat(parking.getFreeTruckPlacesCount(), is(0));
        assertTrue(parking.clearPlace(truck));
        assertThat(parking.getFreeTruckPlacesCount(), is(1));
    }

    @Ignore
    @Test
    public void whenClearTwoCarsPlacesFromTruck() {
        BaseParking parking = new Parking(2, 0);
        Vehicle truck = new Truck(2, "some truck", "O000OO");
        assertThat(parking.getFreeCarPlacesCount(), is(2));
        assertTrue(parking.takePlace(truck));
        assertThat(parking.getFreeCarPlacesCount(), is(0));
        assertTrue(parking.clearPlace(truck));
        assertThat(parking.getFreeTruckPlacesCount(), is(2));
    }

    @Ignore
    @Test
    public void whenHasOnePlaceForCarAndOnePlaceForTruck() {
        BaseParking parking = new Parking(1, 1);
        assertThat(parking.getFreeCarPlacesCount(), is(1));
        assertThat(parking.getFreeTruckPlacesCount(), is(1));
        assertTrue(parking.takePlace(new Car("some car", "O000OO")));
        assertTrue(parking.takePlace(new Truck(2, "some truck", "O001OO")));
        assertThat(parking.getFreeCarPlacesCount(), is(0));
        assertThat(parking.getFreeTruckPlacesCount(), is(0));
    }

    @Ignore
    @Test
    public void whenHasOnePlaceForCarAndNoPlacesForTruck() {
        BaseParking parking = new Parking(1, 0);
        assertThat(parking.getFreeCarPlacesCount(), is(1));
        assertThat(parking.getFreeTruckPlacesCount(), is(0));
        assertTrue(parking.takePlace(new Car("some car", "O000OO")));
        assertFalse(parking.takePlace(new Truck(2, "some truck", "O001OO")));
    }

    @Ignore
    @Test
    public void whenHasOnePlaceForTruckAndNoPlacesForCar() {
        BaseParking parking = new Parking(0, 1);
        assertThat(parking.getFreeCarPlacesCount(), is(0));
        assertThat(parking.getFreeTruckPlacesCount(), is(1));
        assertTrue(parking.takePlace(new Truck(2, "some truck", "O000OO")));
        assertFalse(parking.takePlace(new Car("some car", "O001OO")));
    }

    @Ignore
    @Test
    public void whenTruckPlacedOnTwoCarPlaces() {
        BaseParking parking = new Parking(3, 0);
        assertThat(parking.getFreeCarPlacesCount(), is(3));
        assertThat(parking.getFreeTruckPlacesCount(), is(0));
        assertTrue(parking.takePlace(new Car("some car", "O000OO")));
        assertThat(parking.getFreeCarPlacesCount(), is(2));
        assertTrue(parking.takePlace(new Truck(2, "some truck", "O001OO")));
        assertThat(parking.getFreeCarPlacesCount(), is(0));
    }

    @Ignore
    @Test
    public void whenTruckCanNotBePlacedOnCarPlace() {
        BaseParking parking = new Parking(3, 0);
        assertThat(parking.getFreeCarPlacesCount(), is(3));
        assertThat(parking.getFreeTruckPlacesCount(), is(0));
        assertTrue(parking.takePlace(new Car("first car", "O001OO")));
        assertTrue(parking.takePlace(new Car("second car", "O002OO")));
        assertThat(parking.getFreeCarPlacesCount(), is(1));
        assertFalse(parking.takePlace(new Truck(2, "some truck", "O003OO")));
        assertThat(parking.getFreeCarPlacesCount(), is(1));
    }

    @Ignore
    @Test
    public void whenTruckCanNotBePlacedBetweenTwoCars() {
        BaseParking parking = new Parking(3, 0);
        Vehicle secondCar = new Car("second car", "O000OO");
        parking.takePlace(new Car("first car", "O001OO"));
        parking.takePlace(secondCar);
        parking.takePlace(new Car("third car", "O002OO"));
        assertThat(parking.getFreeCarPlacesCount(), is(0));
        assertTrue(parking.clearPlace(secondCar));
        assertThat(parking.getFreeCarPlacesCount(), is(1));
        assertFalse(parking.takePlace(new Truck(2, "some truck", "O003OO")));
    }
}
