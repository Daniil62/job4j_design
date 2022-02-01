package ru.job4j.chapter1.ood.lsp.violation;

import java.util.List;

public class ForgetfulAntiLSPTypography<T> extends Typography<T> {

    /**
     Первый пример нарушения LSP.
     В методе забыта "проверка на оплату"
     из родительского класса.
     Нарушено (ослабленно) постусловие.
     */
    @Override
    public List<T> launchPrinting(Order<T> order, double cache) {
        calculatePrice(order.getCount());
        return print(order);
    }
}
