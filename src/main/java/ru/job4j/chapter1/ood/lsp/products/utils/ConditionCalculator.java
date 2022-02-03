package ru.job4j.chapter1.ood.lsp.products.utils;

import ru.job4j.chapter1.ood.lsp.products.model.Food;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ConditionCalculator {

    public static double checkCondition(Food product) {
        LocalDate expiryDate = product.getExpiryDate();
        long fullTerm = ChronoUnit.DAYS.between(product.getCreateDate(), expiryDate);
        return (double) 100 / fullTerm * (fullTerm - ChronoUnit.DAYS.between(LocalDate.now(), expiryDate));
    }
}
