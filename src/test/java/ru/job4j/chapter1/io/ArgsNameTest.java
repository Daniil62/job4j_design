package ru.job4j.chapter1.io;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

public class ArgsNameTest {

    @Test
    public void whenGetFirst() {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        assertThat(jvm.get("Xmx"), is("512"));
    }

    @Test
    public void whenGetFirstReorder() {
        ArgsName jvm = ArgsName.of(new String[] {"-encoding=UTF-8", "-Xmx=512"});
        assertThat(jvm.get("Xmx"), is("512"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetNotExist() {
        ArgsName jvm = ArgsName.of(new String[] {});
        jvm.get("Xmx");
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenWrongSomeArgument() {
        ArgsName.of(new String[] {"-enconding=UTF-8", "-Xmx="});
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenBeginningPatternedSymbolMissing() {
        ArgsName.of(new String[] {"enconding=UTF-8"});
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCombiningPatternedSymbolMissing() {
        ArgsName.of(new String[] {"-enconding UTF-8"});
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCombiningPatternedSymbolDoubles() {
        ArgsName.of(new String[] {"-enconding==UTF-8"});
    }
}
