package ru.job4j.chapter1.ood.kiss;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class MaxMin {

    private <T> T compare(List<T> value, Predicate<Integer> predicate, Comparator<T> comparator) {
        T result = value.get(0);
        for (T element : value) {
            if (predicate.test(comparator.compare(element, result))) {
                result = element;
            }
        }
        return result;
    }

    public <T> T max(List<T> value, Comparator<T> comparator) {
        return compare(value, p -> p > 0, comparator);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return compare(value, p -> p < 0, comparator);
    }
}