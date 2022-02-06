package ru.job4j.chapter1.ood.lsp.parking;

import ru.job4j.chapter1.ood.lsp.parking.model.Vehicle;

public abstract class BaseParking {

    protected Vehicle[] carPlaces;
    protected Vehicle[] truckPlaces;

    public BaseParking(int carPlacesCount, int truckPlacesCount) {
        carPlaces = new Vehicle[carPlacesCount];
        truckPlaces = new Vehicle[truckPlacesCount];
    }

    public BaseParking(Vehicle[] carPlaces, Vehicle[] truckPlaces) {
        this.carPlaces = carPlaces;
        this.truckPlaces = truckPlaces;
    }

    public abstract int getCarPlacesCount();

    public abstract int getTruckPlacesCount();

    public abstract int getAllPlacesCount();

    public abstract int getFreeCarPlacesCount();

    public abstract int getFreeTruckPlacesCount();

    public abstract boolean hasCarPlace();

    public abstract boolean hasTruckPlace();

    public abstract boolean clearPlace(Vehicle vehicle);

    public abstract boolean takePlace(Vehicle vehicle);
}
