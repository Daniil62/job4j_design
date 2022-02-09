package ru.job4j.chapter1.ood.lsp.parking.model;

import ru.job4j.chapter1.ood.lsp.parking.BaseParking;

import java.util.ArrayList;
import java.util.List;

public class Parking extends BaseParking {

    private List<Integer> freeCarPlacesForTruck = new ArrayList<>();

    public Parking(int carPlacesCount, int truckPlacesCount) {
        super(carPlacesCount, truckPlacesCount);
    }

    public Parking(Vehicle[] carPlaces, Vehicle[] truckPlaces) {
        super(carPlaces, truckPlaces);
    }

    @Override
    public boolean canTakePlace(Vehicle vehicle) {
        return vehicle.size == Car.SIZE ? freeCarPlaces > 0 : (freeTruckPlaces > 0
                || findCarPlacesForTruck(vehicle).size() == vehicle.size);
    }

    @Override
    public boolean clearPlace(Vehicle vehicle) {
        boolean result = false;
        int carPlacesLength = carPlaces.length;
        if (vehicle != null) {
            if (vehicle.size > Car.SIZE && freeTruckPlaces < carPlacesLength) {
                result = clearPlaces(truckPlaces, vehicle);
                if (!result && freeCarPlaces < carPlacesLength) {
                    result = clearPlaces(carPlaces, vehicle);
                    if (result) {
                        freeCarPlaces += vehicle.size;
                    }
                } else {
                    ++freeTruckPlaces;
                }
            } else if (freeCarPlaces < carPlacesLength) {
                result = clearPlaces(carPlaces, vehicle);
                if (result) {
                    ++freeCarPlaces;
                }
            }
        }
        return result;
    }

    @Override
    public boolean takePlace(Vehicle vehicle) {
        boolean result = false;
        if (vehicle != null && canTakePlace(vehicle)) {
            if (vehicle.size == Car.SIZE) {
                for (int i = 0; i < carPlaces.length; i++) {
                    if (carPlaces[i] == null) {
                        carPlaces[i] = vehicle;
                        result = true;
                        --freeCarPlaces;
                        break;
                    }
                }
            } else {
                result = findPlaceForTruck(vehicle);
            }
            freeCarPlacesForTruck.clear();
        }
        return result;
    }

    @Override
    public ParkingInfo getInfo() {
        return new ParkingInfo() {
            @Override
            public int carPlacesCount() {
                return carPlaces.length;
            }

            @Override
            public int truckPlacesCount() {
                return truckPlaces.length;
            }

            @Override
            public int allPlacesCount() {
                return carPlaces.length + truckPlaces.length;
            }

            @Override
            public int freeCarPlacesCount() {
                return freeCarPlaces;
            }

            @Override
            public int freeTruckPlacesCount() {
                return freeTruckPlaces;
            }
        };
    }

    private boolean findPlaceForTruck(Vehicle vehicle) {
        boolean result = false;
        if (freeTruckPlaces > 0) {
            for (int i = 0; i < truckPlaces.length; i++) {
                if (truckPlaces[i] == null) {
                    truckPlaces[i] = vehicle;
                    result = true;
                    --freeTruckPlaces;
                    break;
                }
            }
        } else {
            result = freeCarPlacesForTruck.size() == vehicle.size;
            if (result) {
                parkTruckOnCarPlaces(vehicle);
            }
        }
        return result;
    }

    private List<Integer> findCarPlacesForTruck(Vehicle vehicle) {
        int vehicleSize = vehicle.size;
        List<Integer> result = new ArrayList<>();
        if (freeCarPlaces >= vehicleSize) {
            int freeIndex = 0;
            for (int i = 0; i < carPlaces.length; i++) {
                if (carPlaces[i] == null) {
                    result.add(i);
                    if (freeIndex != i && freeIndex != i - 1) {
                        result.clear();
                    }
                    if (result.size() == vehicleSize) {
                        freeCarPlacesForTruck.addAll(result);
                        break;
                    }
                    freeIndex = i;
                }
            }
        }
        return result;
    }

    private void parkTruckOnCarPlaces(Vehicle vehicle) {
        for (int freePlace : freeCarPlacesForTruck) {
            carPlaces[freePlace] = vehicle;
        }
        freeCarPlaces -= vehicle.size;
    }

    private boolean clearPlaces(Vehicle[] places, Vehicle vehicle) {
        boolean result = false;
        int vehicleSize = vehicle.size;
        int count = 0;
        for (int i = 0; i < places.length; i++) {
            Vehicle place = places[i];
            if (place != null && place.equals(vehicle)) {
                result = true;
                places[i] = null;
                if (++count == vehicleSize) {
                    break;
                }
            }
        }
        return result;
    }
}
