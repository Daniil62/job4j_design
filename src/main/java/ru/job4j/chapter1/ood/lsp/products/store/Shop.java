package ru.job4j.chapter1.ood.lsp.products.store;

import ru.job4j.chapter1.ood.lsp.products.model.Food;
import ru.job4j.chapter1.ood.lsp.products.utils.ConditionCalculator;

import java.util.List;

public class Shop extends Storage {

    private static final int MIN_THRESHOLD = 25;
    private static final int MAX_THRESHOLD = 100;
    private static final int DISCOUNT_ACTIVATION_THRESHOLD = 75;
    private static final int DISCOUNT = 50;

    public Shop(List<Food> container) {
        super(container);
        predicate = p -> p != null && ConditionCalculator.checkCondition(p) >= MIN_THRESHOLD
                        && ConditionCalculator.checkCondition(p) < MAX_THRESHOLD;
    }

    @Override
    public boolean add(Food product) {
        boolean result = false;
        if (predicate.test(product)) {
            if (ConditionCalculator.checkCondition(product) > DISCOUNT_ACTIVATION_THRESHOLD) {
                product.setDiscount(DISCOUNT);
            }
            result = container.add(product);
        }
        return result;
    }
}
