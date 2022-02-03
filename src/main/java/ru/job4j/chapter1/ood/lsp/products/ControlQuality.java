package ru.job4j.chapter1.ood.lsp.products;

import ru.job4j.chapter1.ood.lsp.products.model.Food;
import ru.job4j.chapter1.ood.lsp.products.store.Storage;

import java.util.ArrayList;
import java.util.List;

public class ControlQuality {

    private List<Food> container = new ArrayList<>();
    private List<Food> tempContainer = new ArrayList<>();

    private void confiscation(Storage storage) {
        tempContainer = storage.getUnnecessary();
        storage.removeAll(tempContainer);
    }

    private void supply(Storage storage) {
        List<Food> tempList = new ArrayList<>();
        for (Food product : container) {
            if (storage.add(product)) {
                tempList.add(product);
            }
        }
        container.removeAll(tempList);
    }

    public void revision(Storage storage) {
        confiscation(storage);
        supply(storage);
        container.addAll(tempContainer);
        tempContainer.clear();
    }

    public void automaticDistribution(List<Storage> storageList) {
        for (Storage storage : storageList) {
            revision(storage);
        }
        container.clear();
    }

    public int getTotalCount() {
        return container.size();
    }

    public void fillContainer(List<Food> products) {
        container.addAll(products);
    }

    public Food getProductById(int id) {
        Food result = null;
        if (id >= 0 && id < container.size()) {
            result = container.get(id);
        }
        return result;
    }
}
