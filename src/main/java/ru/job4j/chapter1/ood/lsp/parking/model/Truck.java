package ru.job4j.chapter1.ood.lsp.parking.model;

public class Truck extends Vehicle {

    private static final int DEFAULT_MIN_SIZE = 2;

    public Truck(int size, String name, String serialPlate) {
        this.name = name;
        this.serialPlate = serialPlate;
        this.size = Math.max(size, DEFAULT_MIN_SIZE);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Truck)) {
            return false;
        }
        Truck truck = (Truck) o;
        return size == truck.size
                && name.equals(truck.name)
                && serialPlate.equals(truck.serialPlate);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result *= Integer.hashCode(size);
        result *= serialPlate.hashCode();
        return result;
    }
}
