package ru.job4j.chapter1.iterator;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.Test;

import java.util.NoSuchElementException;

public class BackwardArrayItTest {

    @Test
    public void whenMultiCallHasNextThenTrue() {
        BackwardArrayIt<Integer> it = new BackwardArrayIt<>(new Integer[] {1, 2, 3});
        assertThat(it.hasNext(), is(true));
    }

    @Test
    public void whenReadIntSequence() {
        BackwardArrayIt<Integer> it = new BackwardArrayIt<>(new Integer[] {1, 2, 3});
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(1));
    }

    @Test
    public void whenReadCharSequence() {
        BackwardArrayIt<Character> it = new BackwardArrayIt<>(new Character[] {'A', 'B', 'C'});
        assertThat(it.next(), is('C'));
        assertThat(it.next(), is('B'));
        assertThat(it.next(), is('A'));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenNextFromEmpty() {
        BackwardArrayIt<Integer> it = new BackwardArrayIt<>(new Integer[] {});
        it.next();
    }
}
