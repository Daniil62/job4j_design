package ru.job4j.chapter1.ood.lsp.violation;

import java.util.ArrayList;
import java.util.List;

public class Typography<T> {

    protected int minCopiesCountThreshold = 500;
    protected int maxCopiesCountThreshold = 700000;
    private static final String MIN_ERROR_MESSAGE = "Not enough copies to start!";
    private static final String MAX_ERROR_MESSAGE = "exceeded the maximum number!";
    protected CacheRegister boxOffice = new CacheRegister(15.50);


    protected void validate(int copies) {
        if (copies < minCopiesCountThreshold) {
            throw new IllegalArgumentException(MIN_ERROR_MESSAGE);
        }
        if (copies > maxCopiesCountThreshold) {
            throw new IllegalArgumentException(MAX_ERROR_MESSAGE);
        }
    }

    public double calculatePrice(int copies) {
        validate(copies);
        return boxOffice.calculate(copies);
    }

    public List<T> launchPrinting(Order<T> order, double cache) {
        calculatePrice(order.getCount());
        boxOffice.checkPayment(cache);
        return print(order);
    }

    protected List<T> print(Order<T> order) {
        List<T> box = new ArrayList<>();
        int countdown = order.getCount();
        while (countdown > 0) {
            box.add(order.getSample());
            countdown--;
        }
        return box;
    }
}
