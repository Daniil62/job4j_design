package ru.job4j.chapter1.ood.dip.violation;

public class SimpleFormValidator implements FormValidator {

    private static final String ERROR_MESSAGE = "\u001b[31m Form must be completely filled.\u001b[0m";

    @Override
    public void validate(Form form) {
        if (form == null || form.getCompleteForm().isEmpty()) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
    }
}
