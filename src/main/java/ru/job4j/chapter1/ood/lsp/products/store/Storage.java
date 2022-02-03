package ru.job4j.chapter1.ood.lsp.products.store;

import ru.job4j.chapter1.ood.lsp.products.model.Food;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public abstract class Storage {

    protected List<Food> container;

    protected Predicate<Food> predicate = p -> true;

    public Storage(List<Food> list) {
        this.container = list;
    }

    public abstract boolean add(Food product);

    public void removeAll(List<Food> list) {
        container.removeAll(list);
    }

    protected boolean check(Food product) {
        return predicate.test(product);
    }

    public List<Food> getUnnecessary() {
        return container.stream().filter(p -> !check(p)).collect(Collectors.toList());
    }

    public int getTotalCount() {
        return container.size();
    }

    public Food getProductById(int id) {
        Food result = null;
        if (id >= 0 && id < container.size()) {
            result = container.get(id);
        }
        return result;
    }
}
