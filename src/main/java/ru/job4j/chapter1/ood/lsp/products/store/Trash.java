package ru.job4j.chapter1.ood.lsp.products.store;

import ru.job4j.chapter1.ood.lsp.products.model.Food;
import ru.job4j.chapter1.ood.lsp.products.utils.ConditionCalculator;

import java.util.List;

public class Trash extends Storage {

    private static final int THRESHOLD = 90;

    public Trash(List<Food> container) {
        super(container);
        predicate = p -> p != null && ConditionCalculator.checkCondition(p) >= THRESHOLD;
    }

    @Override
    public boolean add(Food product) {
        boolean result = false;
        if (predicate.test(product)) {
            result = container.add(product);
        }
        return result;
    }
}
