package ru.job4j.chapter1.ood.lsp.violation;

public class CacheRegister {

    private double unitPrice;
    private double sum;
    private static final String ERROR_MESSAGE = "the order must be paid for! Order sum = ";

    public CacheRegister(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double calculate(int count) {
        sum = count * unitPrice;
        return sum;
    }

    public void checkPayment(double cache) {
        if (cache < sum) {
            throw new IllegalArgumentException(ERROR_MESSAGE + sum);
        }
        System.out.println("order sum = " + sum
                + System.lineSeparator()
                + "paid = " + cache
                + System.lineSeparator()
                + "change = " + (cache - sum)
                + System.lineSeparator());
    }
}
