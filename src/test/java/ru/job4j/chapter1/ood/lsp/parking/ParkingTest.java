package ru.job4j.chapter1.ood.lsp.parking;

import org.junit.Test;
import ru.job4j.chapter1.ood.lsp.parking.model.Car;
import ru.job4j.chapter1.ood.lsp.parking.model.Parking;
import ru.job4j.chapter1.ood.lsp.parking.model.Truck;
import ru.job4j.chapter1.ood.lsp.parking.model.Vehicle;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ParkingTest {

    @Test
    public void whenCanParkCar() {
        BaseParking parking = new Parking(1, 0);
        assertTrue(parking.canTakePlace(new Car("some car", "O123OO")));
    }

    @Test
    public void whenCanParkTruck() {
        BaseParking parking = new Parking(0, 1);
        assertTrue(parking.canTakePlace(new Truck(2, "some truck", "O123OO")));
    }

    @Test
    public void whenCanParkTruckOnCarPlaces() {
        BaseParking parking = new Parking(3, 0);
        assertTrue(parking.canTakePlace(new Truck(3, "some truck", "O123OO")));
    }

    @Test
    public void whenClearPlaceFromCar() {
        BaseParking parking = new Parking(1, 1);
        Vehicle car = new Car("some car", "O000OO");
        assertThat(parking.getInfo().freeCarPlacesCount(), is(1));
        assertTrue(parking.takePlace(car));
        assertThat(parking.getInfo().freeCarPlacesCount(), is(0));
        assertTrue(parking.clearPlace(car));
        assertThat(parking.getInfo().freeCarPlacesCount(), is(1));
    }

    @Test
    public void whenClearTruckPlace() {
        BaseParking parking = new Parking(1, 1);
        Vehicle truck = new Truck(2, "some truck", "O000OO");
        assertThat(parking.getInfo().freeTruckPlacesCount(), is(1));
        assertTrue(parking.takePlace(truck));
        assertThat(parking.getInfo().freeTruckPlacesCount(), is(0));
        assertTrue(parking.clearPlace(truck));
        assertThat(parking.getInfo().freeTruckPlacesCount(), is(1));
    }

    @Test
    public void whenClearTwoCarsPlacesFromTruck() {
        BaseParking parking = new Parking(2, 0);
        Vehicle truck = new Truck(2, "some truck", "O000OO");
        assertThat(parking.getInfo().freeCarPlacesCount(), is(2));
        assertTrue(parking.takePlace(truck));
        assertThat(parking.getInfo().freeCarPlacesCount(), is(0));
        assertTrue(parking.clearPlace(truck));
        assertThat(parking.getInfo().freeCarPlacesCount(), is(2));
    }

    @Test
    public void whenHasOnePlaceForCarAndOnePlaceForTruck() {
        BaseParking parking = new Parking(1, 1);
        assertThat(parking.getInfo().freeCarPlacesCount(), is(1));
        assertThat(parking.getInfo().freeTruckPlacesCount(), is(1));
        assertTrue(parking.takePlace(new Car("some car", "O000OO")));
        assertTrue(parking.takePlace(new Truck(2, "some truck", "O001OO")));
        assertThat(parking.getInfo().freeCarPlacesCount(), is(0));
        assertThat(parking.getInfo().freeTruckPlacesCount(), is(0));
    }

    @Test
    public void whenHasOnePlaceForCarAndNoPlacesForTruck() {
        BaseParking parking = new Parking(1, 0);
        assertThat(parking.getInfo().freeCarPlacesCount(), is(1));
        assertThat(parking.getInfo().freeTruckPlacesCount(), is(0));
        assertTrue(parking.takePlace(new Car("some car", "O000OO")));
        assertFalse(parking.takePlace(new Truck(2, "some truck", "O001OO")));
    }

    @Test
    public void whenHasOnePlaceForTruckAndNoPlacesForCar() {
        BaseParking parking = new Parking(0, 1);
        assertThat(parking.getInfo().freeCarPlacesCount(), is(0));
        assertThat(parking.getInfo().freeTruckPlacesCount(), is(1));
        assertTrue(parking.takePlace(new Truck(2, "some truck", "O000OO")));
        assertFalse(parking.takePlace(new Car("some car", "O001OO")));
    }

    @Test
    public void whenTruckPlacedOnTwoCarPlaces() {
        BaseParking parking = new Parking(3, 0);
        assertThat(parking.getInfo().freeCarPlacesCount(), is(3));
        assertThat(parking.getInfo().freeTruckPlacesCount(), is(0));
        assertTrue(parking.takePlace(new Car("some car", "O000OO")));
        assertThat(parking.getInfo().freeCarPlacesCount(), is(2));
        assertTrue(parking.takePlace(new Truck(2, "some truck", "O001OO")));
        assertThat(parking.getInfo().freeCarPlacesCount(), is(0));
    }

    @Test
    public void whenTruckCanNotBePlacedOnCarPlace() {
        BaseParking parking = new Parking(3, 0);
        assertThat(parking.getInfo().freeCarPlacesCount(), is(3));
        assertThat(parking.getInfo().freeTruckPlacesCount(), is(0));
        assertTrue(parking.takePlace(new Car("first car", "O001OO")));
        assertTrue(parking.takePlace(new Car("second car", "O002OO")));
        assertThat(parking.getInfo().freeCarPlacesCount(), is(1));
        assertFalse(parking.takePlace(new Truck(2, "some truck", "O003OO")));
        assertThat(parking.getInfo().freeCarPlacesCount(), is(1));
    }

    @Test
    public void whenTruckCanNotBePlacedBetweenTwoCars() {
        BaseParking parking = new Parking(3, 0);
        Vehicle secondCar = new Car("second car", "O000OO");
        parking.takePlace(new Car("first car", "O001OO"));
        parking.takePlace(secondCar);
        parking.takePlace(new Car("third car", "O002OO"));
        assertThat(parking.getInfo().freeCarPlacesCount(), is(0));
        assertTrue(parking.clearPlace(secondCar));
        assertThat(parking.getInfo().freeCarPlacesCount(), is(1));
        assertFalse(parking.takePlace(new Truck(2, "some truck", "O003OO")));
    }

    @Test
    public void whenFreeCarPlacesCountMoreThanTruckSizeButTruckCanNotPlaced() {
        BaseParking parking = new Parking(8, 0);
        Vehicle firstCar = new Car("second car", "O111OO");
        Vehicle fourthCar = new Car("fourth car", "O444OO");
        Vehicle sixthCar = new Car("sixth car", "O666OO");
        parking.takePlace(firstCar);
        parking.takePlace(new Car("second car", "O222OO"));
        parking.takePlace(new Car("third car", "O333OO"));
        parking.takePlace(fourthCar);
        parking.takePlace(new Car("fifth car", "O555OO"));
        parking.takePlace(sixthCar);
        parking.takePlace(new Car("seventh car", "O777OO"));
        assertThat(parking.getInfo().freeCarPlacesCount(), is(1));
        assertThat(parking.getInfo().allPlacesCount(), is(8));
        assertTrue(parking.clearPlace(firstCar));
        assertTrue(parking.clearPlace(fourthCar));
        assertTrue(parking.clearPlace(sixthCar));
        assertThat(parking.getInfo().freeCarPlacesCount(), is(4));
        assertFalse(parking.takePlace(new Truck(2, "some truck", "O003OO")));
    }
}
