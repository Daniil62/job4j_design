package ru.job4j.chapter1.ood.dip.violation;

import java.util.ArrayList;
import java.util.List;

public class MemoryLoader implements FormLoader {


    private final List<String> forms = new ArrayList<>();
    private final FormValidator validator;

    public MemoryLoader(FormValidator validator) {
        this.validator = validator;
    }

    @Override
    public void load(SomeForm form) {
        validator.validate(form);
        forms.add(form.getCompleteForm());
    }

    public String getForm(int index) {
        String result = "";
        if (index >= 0 && index < forms.size()) {
            result = forms.get(index);
        }
        return result;
    }
}
