package ru.job4j.chapter1.ood.srp.violation;

public class StringModifier implements Modifier<String> {

    @Override
    public String modify(String string) {
        return "\"" + string + "\"";
    }
}
