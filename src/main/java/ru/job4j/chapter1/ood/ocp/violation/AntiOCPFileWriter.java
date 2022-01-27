package ru.job4j.chapter1.ood.ocp.violation;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;


/**
 * Первый пример нарушения принципа OCP.
 * Класс не реализует абстракцию, а существует
 * сам по себе, имея final метод для записи в файл.
 * Другими словами - класс закрыт для расширения.
 */
public class AntiOCPFileWriter {

    private static final String PATH = "./music_info.txt";

    /**
     * Второй пример нарушения принципа OCP.
     * Метод принимает в параметры реализацию
     * вместо абстракции.
     */
    public final void writeInFile(DeathMetal model) {
        try (PrintWriter writer = new PrintWriter(new BufferedOutputStream(new FileOutputStream(PATH)))) {
            writer.println(model.getInfo());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
