package ru.job4j.chapter1.list.map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;

public class SimpleMapTest {

    private SimpleMap<Integer, String> map;
    String first;

    @Before
    public void init() {
        map = new SimpleMap<>();
        first = "first";
        map.put(1, first);
    }

    @Test
    public void whenPutSuccess() {
        Assert.assertTrue(map.put(2, "second"));
        Assert.assertThat(map.size(), is(2));
    }

    @Test
    public void whenPutManyUsers() {
        Assert.assertThat(map.size(), is(1));
        map.put(2, "second");
        map.put(3, "third");
        map.put(4, "fourth");
        map.put(5, "fifth");
        map.put(6, "sixth");
        Assert.assertTrue(map.put(7, "seventh"));
        Assert.assertThat(map.size(), is(7));
    }

    @Test
    public void whenPutWithNullKey() {
        Assert.assertTrue(map.put(null, "null value"));
        Assert.assertThat(map.size(), is(2));
        Assert.assertThat(map.get(null), is("null value"));
    }

    @Test
    public void whenPutFailed() {
        Assert.assertFalse(map.put(1, "first"));
        Assert.assertThat(map.size(), is(1));
    }

    @Test
    public void whenReturnUser() {
        Assert.assertThat(map.get(1), is(first));
    }

    @Test
    public void whenReturnUserAfterTableIncrease() {
        SimpleMap<Integer, String> map = new SimpleMap<>(2);
        map.put(null, "zero");
        map.put(1, "one");
        map.put(2, "two");
        Assert.assertThat(map.size(), is(3));
        Assert.assertThat(map.get(null), is("zero"));
        Assert.assertThat(map.get(1), is("one"));
        Assert.assertThat(map.get(2), is("two"));
    }

    @Test
    public void whenReturnNull() {
        Assert.assertNull(map.get(98));
    }

    @Test
    public void whenRemoveSuccessful() {
        map.put(2, "two");
        Assert.assertThat(map.size(), is(2));
        Assert.assertTrue(map.remove(2));
        Assert.assertThat(map.size(), is(1));
    }

    @Test
    public void whenRemoveFailed() {
        Assert.assertFalse(map.remove(98));
    }

    @Test
    public void whenIteratorHasNextAndReturnUser() {
        map.put(0, "");
        Iterator<Integer> it = map.iterator();
        Assert.assertTrue(it.hasNext());
        Assert.assertThat(it.next(), is(0));
        Assert.assertTrue(it.hasNext());
        Assert.assertThat(it.next(), is(1));
        Assert.assertFalse(it.hasNext());
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenIteratorThrowError() {
        Iterator<Integer> it = map.iterator();
        Assert.assertTrue(it.hasNext());
        map.put(2, "two");
        it.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenMapIsEmptyAndIteratorThrowError() {
        map.remove(1);
        Iterator<Integer> it = map.iterator();
        Assert.assertThat(map.size(), is(0));
        Assert.assertFalse(it.hasNext());
        it.next();
    }
}
