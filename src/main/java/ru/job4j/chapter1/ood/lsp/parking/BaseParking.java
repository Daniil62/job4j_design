package ru.job4j.chapter1.ood.lsp.parking;

import ru.job4j.chapter1.ood.lsp.parking.model.Vehicle;

import java.util.Arrays;

public abstract class BaseParking {

    protected Vehicle[] carPlaces;
    protected Vehicle[] truckPlaces;

    protected int freeCarPlaces;
    protected int freeTruckPlaces;

    public BaseParking(int carPlacesCount, int truckPlacesCount) {
        carPlaces = new Vehicle[carPlacesCount];
        truckPlaces = new Vehicle[truckPlacesCount];
        freeCarPlaces = calculateFreePlaces(carPlaces);
        freeTruckPlaces = calculateFreePlaces(truckPlaces);
    }

    public BaseParking(Vehicle[] carPlaces, Vehicle[] truckPlaces) {
        this.carPlaces = Arrays.copyOf(carPlaces, carPlaces.length);
        this.truckPlaces = Arrays.copyOf(carPlaces, truckPlaces.length);
        freeCarPlaces = calculateFreePlaces(carPlaces);
        freeTruckPlaces = calculateFreePlaces(truckPlaces);
    }

    public abstract boolean canTakePlace(Vehicle vehicle);

    public abstract boolean clearPlace(Vehicle vehicle);

    public abstract boolean takePlace(Vehicle vehicle);

    protected int calculateFreePlaces(Vehicle[] places) {
        int result = 0;
        for (Vehicle vehicle : places) {
            if (vehicle == null) {
                ++result;
            }
        }
        return result;
    }

    protected abstract static class ParkingInfo {

        public abstract int carPlacesCount();

        public abstract int truckPlacesCount();

        public abstract int allPlacesCount();

        public abstract int freeCarPlacesCount();

        public abstract int freeTruckPlacesCount();
    }

    public abstract ParkingInfo getInfo();
}
