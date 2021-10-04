package ru.job4j.chapter1.list.set;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleSetTest {

    private Set<Integer> set;

    @Before
    public void init() {
        set = new SimpleSet<>();
    }

    @Test
    public void whenAddNonNull() {
        assertTrue(set.add(1));
        assertTrue(set.contains(1));
        assertFalse(set.add(1));
    }

    @Test
    public void whenAddNull() {
        assertTrue(set.add(null));
        assertTrue(set.contains(null));
        assertFalse(set.add(null));
    }

    @Test
    public void whenIteratorDoesNotHasNext() {
        assertFalse(set.iterator().hasNext());
    }

    @Test
    public void whenIteratorHasNext() {
        set.add(78);
        assertTrue(set.iterator().hasNext());
    }

    @Test
    public void whenValuesFetchByIterator() {
        set.add(1);
        set.add(2);
        Iterator<Integer> it = set.iterator();
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
    }
}
