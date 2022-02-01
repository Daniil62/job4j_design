package ru.job4j.chapter1.ood.lsp.violation;

public class GreedyAntiLSPTypography<T> extends Typography<T> {

    private static final int MIN_COPIES_COUNT_THRESHOLD = 5000;
    private static final String MIN_ERROR_MESSAGE = "Not enough copies to start!";
    private static final String MAX_ERROR_MESSAGE = "exceeded the maximum number!";

    /**
     Второй пример нарушения LSP.
     Наследуемое поле родительского
     класса отвечающее за ограничение
     значения нижнего порога, заменено
     в первом условии переопределенного
     метода на несоответствующее увеличенное
     значение. Нарушено (усилено) предусловие.
     */
    @Override
    protected void validate(int copies) {
        if (copies < MIN_COPIES_COUNT_THRESHOLD) {
            throw new IllegalArgumentException(MIN_ERROR_MESSAGE);
        }
        if (copies > maxCopiesCountThreshold) {
            throw new IllegalArgumentException(MAX_ERROR_MESSAGE);
        }
    }
}
