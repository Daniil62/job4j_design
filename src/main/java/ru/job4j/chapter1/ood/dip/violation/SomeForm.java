package ru.job4j.chapter1.ood.dip.violation;

import java.util.Set;

public class SomeForm extends Form {

    private static final String ERROR_MESSAGE = "\u001b[31m This field must be filled!\u001b[0m";

    public SomeForm(String name, Set<String> fieldNames) {
        super(name, fieldNames);
    }

    @Override
    public Iterable<String> getFields() {
        return fields.keySet();
    }

    @Override
    public boolean fill(String key, String value) {
        boolean result = fields.containsKey(key) && validate(value);
        if (result) {
            fields.put(key, value);
        }
        return result;
    }

    @Override
    public String getCompleteForm() {
        StringBuilder builder = new StringBuilder();
        if (!fields.containsValue(null)) {
            builder.append(name)
                    .append(System.lineSeparator());
            for (String key : fields.keySet()) {
                builder.append(key)
                        .append(" ")
                        .append(fields.get(key))
                        .append(System.lineSeparator());
            }
        }
        return builder.toString();
    }

    public String getName() {
        return name;
    }

    /**
     * Третий пример нарушения DIP.
     * Внутри метода использовано логирование
     * напрямую зависящее от вывода в консоль.
     */
    private boolean validate(String value) {
        boolean result = true;
        if (value == null || value.isEmpty()) {
            System.out.println(ERROR_MESSAGE);
            result = false;
        }
        return result;
    }

}
