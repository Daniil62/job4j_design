package ru.job4j.chapter1.ood.lsp.parking.model;

import ru.job4j.chapter1.ood.lsp.parking.BaseParking;

public class Parking extends BaseParking {

    public Parking(int carPlacesCount, int truckPlacesCount) {
        super(carPlacesCount, truckPlacesCount);
    }

    public Parking(Vehicle[] carPlaces, Vehicle[] truckPlaces) {
        super(carPlaces, truckPlaces);
    }

    @Override
    public int getCarPlacesCount() {
        return 0;
    }

    @Override
    public int getTruckPlacesCount() {
        return 0;
    }

    @Override
    public int getAllPlacesCount() {
        return 0;
    }

    @Override
    public int getFreeCarPlacesCount() {
        return 0;
    }

    @Override
    public int getFreeTruckPlacesCount() {
        return 0;
    }

    @Override
    public boolean hasCarPlace() {
        return false;
    }

    @Override
    public boolean hasTruckPlace() {
        return false;
    }

    @Override
    public boolean clearPlace(Vehicle vehicle) {
        return false;
    }

    @Override
    public boolean takePlace(Vehicle vehicle) {
        return false;
    }

    private int[] findPlaceForTruck() {
        return null;
    }
}
