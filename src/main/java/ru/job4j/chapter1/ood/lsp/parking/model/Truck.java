package ru.job4j.chapter1.ood.lsp.parking.model;

public class Truck extends Vehicle {

    public Truck(int size, String name, String serialPlate) {
        this.name = name;
        this.serialPlate = serialPlate;
        this.size = Math.max(size, 2);
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
