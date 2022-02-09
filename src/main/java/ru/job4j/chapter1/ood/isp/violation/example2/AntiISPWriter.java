package ru.job4j.chapter1.ood.isp.violation.example2;

import java.util.List;

/**
 * Второй приме нарушения ISP.
 * Интерфейс имеет методы подразумевающие
 * запись значений в файл и в базу данных,
 * но у класса его расширяющего, может не быть
 * доступа к базе, или файлу, а во-вторых это
 * может нарушать SRP.
 */
public interface AntiISPWriter<T> {

    public void writeInDB(List<T> values);

    public void writeToFile(List<T> values);
}
