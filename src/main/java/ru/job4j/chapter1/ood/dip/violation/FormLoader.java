package ru.job4j.chapter1.ood.dip.violation;

public interface FormLoader {

    /**
     * Второй пример нарушения DIP.
     * Абстракция зависит от реализации
     * другой абстракции.
     */
    void load(SomeForm form);
}
