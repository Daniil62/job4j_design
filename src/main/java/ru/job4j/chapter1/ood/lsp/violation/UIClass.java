package ru.job4j.chapter1.ood.lsp.violation;

import java.util.List;

public class UIClass {

    Typography<String> printingHouse;

    public UIClass(Typography<String> printingHouse) {
        this.printingHouse = printingHouse;
    }

    public void setPrintingHouse(Typography<String> printingHouse) {
        this.printingHouse = printingHouse;
    }

    public void showPrice(int copies) {
        System.out.println(printingHouse.calculatePrice(copies));
    }

    public List<String> printing(Order<String> order, double cache) {
        return printingHouse.launchPrinting(order, cache);
    }
}
