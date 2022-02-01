package ru.job4j.chapter1.ood.lsp.violation;

public class Order<T> {

    private T sample;
    private int count;

    public Order(T sample, int count) {
        this.sample = sample;
        this.count = count;
    }

    public T getSample() {
        return sample;
    }

    public void setSample(T sample) {
        this.sample = sample;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
