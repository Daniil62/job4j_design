package ru.job4j.chapter1.ood.isp.violation.example1;

/**
 * Пример нарушения принципа разделения интерфейсов.
 * Интерфейс подразумевает что каждый класс его расширяющий,
 * должен читать текст из базы данных, файла и с сайта, что
 * в свою очередь нарушит принцип единой ответственности.
 */
public interface AntiISPReader {

    public String readDB();

    public String readFile(String file);

    public String readWeb(String link);
}
