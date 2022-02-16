package ru.job4j.chapter1.ood.dip.violation;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class FileLoader implements FormLoader {

    private final String path;

    /**
     * Пример нарушения правила инверсии зависимостей.
     * класс зависит от реализации абстракции, а не от
     * нее самой.
     */
    private final SimpleFormValidator validator;

    public FileLoader(String path, SimpleFormValidator validator) {
        this.path = path;
        this.validator = validator;
    }

    /**
     * Нарушение в интерфейсе реализуемом этим
     * классом, соответственно влечет нарушение
     * в этом классе. Метотод принимает реализацию,
     * а не абстракцию.
     */
    @Override
    public void load(SomeForm form) {
        validator.validate(form);
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(path)))) {
            out.println(form.getCompleteForm());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
