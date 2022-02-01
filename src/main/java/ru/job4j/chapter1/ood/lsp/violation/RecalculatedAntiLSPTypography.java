package ru.job4j.chapter1.ood.lsp.violation;

import java.util.List;

public class RecalculatedAntiLSPTypography<T> extends Typography<T> {

    /**
     Третий пример нарушения LSP.
     В переопределенном методе забыт
     "пересчет заказа", который может
     быть "переоформлен" в процессе.
     */
    @Override
    public List<T> launchPrinting(Order<T> order, double cache) {
        validate(order.getCount());
        boxOffice.checkPayment(cache);
        return print(order);
    }
}
