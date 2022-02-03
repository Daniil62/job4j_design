package ru.job4j.chapter1.ood.lsp.products.model;

import java.time.LocalDate;
import java.util.Objects;

public class Food {

    protected String name;
    protected LocalDate createDate;
    protected LocalDate expiryDate;
    protected double price;
    protected int discount;

    public Food(String name, LocalDate createDate, LocalDate expiryDate, double price) {
        this.name = name;
        this.createDate = createDate;
        this.expiryDate = expiryDate;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Food)) {
            return false;
        }
        Food food = (Food) o;
        return Double.compare(food.price, price) == 0
                && Objects.equals(name, food.name)
                && Objects.equals(createDate, food.createDate)
                && Objects.equals(expiryDate, food.expiryDate);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result *= 31 + createDate.hashCode();
        result *= 31 + expiryDate.hashCode();
        result *= 31 + Double.hashCode(price);
        return result;
    }
}
