package ru.job4j.chapter1.ood.lsp.parking.model;

public class Car extends Vehicle {

    public Car(String name, String serialPlate) {
        this.name = name;
        this.serialPlate = serialPlate;
        this.size = 1;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Car)) {
            return false;
        }
        Car car = (Car) o;
        return size == car.size
                && name.equals(car.name)
                && serialPlate.equals(car.serialPlate);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result *= Integer.hashCode(size);
        result *= serialPlate.hashCode();
        return result;
    }
}
