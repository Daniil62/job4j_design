package ru.job4j.chapter1.iterator;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.NoSuchElementException;

public class MatrixItTest {

    @Test
    public void when4El() {
        Integer[][] in = {
                {1}
        };
        MatrixIt<Integer> it = new MatrixIt<>(in);
        assertThat(it.next(), is(1));
    }

    @Test
    public void whenFirstEmptyThenHasNext() {
        String[][] in = {
                {}, {"A"}
        };
        MatrixIt<String> it = new MatrixIt<>(in);
        assertThat(it.hasNext(), is(true));
    }

    @Test
    public void whenRowsHasDifferentSize() {
        Integer[][] in = {
                {1}, {2, 3}
        };
        MatrixIt<Integer> it = new MatrixIt<>(in);
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
    }

    @Test
    public void whenFewEmpty() {
        Integer[][] in = {
                {1}, {}, {}, {}, {5}
        };
        MatrixIt<Integer> it = new MatrixIt<>(in);
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(5));
    }

    @Test
    public void whenEmpty() {
        Integer[][] in = {
                {}
        };
        MatrixIt<Integer> it = new MatrixIt<>(in);
        assertThat(it.hasNext(), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenEmptyThenNext() {
        Integer[][] in = {
                {}
        };
        MatrixIt<Integer> it = new MatrixIt<>(in);
        it.next();
    }

    @Test
    public void whenMultiHasNext() {
        Integer[][] in = {
                {}, {2}
        };
        MatrixIt<Integer> it = new MatrixIt<>(in);
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
    }

    @Test
    public void whenHasNullValueAndEmptyCells() {
        Integer[][] in = {
                {1, null}, {}, {}, {2}
        };
        MatrixIt<Integer> it = new MatrixIt<>(in);
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(Matchers.nullValue()));
        assertThat(it.next(), is(2));
    }

    @Test
    public void whenHasOneElementAndCheckHasNextAfterThisElement() {
        Integer[][] in = {
                {1}
        };
        MatrixIt<Integer> it = new MatrixIt<>(in);
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(false));
    }

    @Test
    public void whenNoElements() {
        Integer[][] in = {
                {}, {}, {}
        };
        MatrixIt<Integer> it = new MatrixIt<>(in);
        assertThat(it.hasNext(), is(false));
    }
}
