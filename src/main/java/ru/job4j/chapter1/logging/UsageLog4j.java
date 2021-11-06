package ru.job4j.chapter1.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {

        byte b = 127;
        short s = 32767;
        int i = 2147483647;
        long l = Long.MAX_VALUE;
        double d = Double.MAX_VALUE;
        float f = Float.MAX_VALUE;
        char c = Character.MAX_VALUE / 2;

        LOG.debug("Byte max value: {},\nShort max value: {},\nInteger max value: {},"
                        + "\nLong max value: {},\nFloat max value: {},\nDouble max value: {},"
                        + "\nCharacter symbol at (all quantity / 2): {},"
                        + "\nBoolean max value: {}\n",
                b, s, i, l, f, d, c, true);

        LOG.trace("trace message");
        LOG.debug("debug message");
        LOG.info("info message");
        LOG.warn("warn message");
        LOG.error("error message");
    }
}
