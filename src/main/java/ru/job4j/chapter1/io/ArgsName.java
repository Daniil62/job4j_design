package ru.job4j.chapter1.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        return values.get(key);
    }

    private void parse(String[] args) {
        primaryValidate(args);
        for (String argument : args) {
            secondaryValidate(argument);
            String[] pair = argument.split("=");
            String key = pair[0];
            values.put(key.substring(1), pair[1]);
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    private void primaryValidate(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("arguments are null! Arguments must follow \"-key=value\" pattern.");
        }
    }

    private void secondaryValidate(String argument) {
        if (!argument.matches("-[^=]+=[^=]+")) {
            throw new IllegalArgumentException("key or value in argument is null "
                    + "or missing beginning symbol \"-\". Maybe combining symbol \"=\""
                    + " is missed or used more than once as well."
                    + " Arguments must follow \"-key=value\" pattern only.");
        }
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
