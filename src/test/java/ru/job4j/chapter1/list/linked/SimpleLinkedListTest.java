package ru.job4j.chapter1.list.linked;

import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;

public class SimpleLinkedListTest {
    @Test
    public void whenAddAndGet() {
        List<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        assertThat(list.get(0), Is.is(1));
        assertThat(list.get(1), Is.is(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetFromOutOfBoundThenExceptionThrown() {
        List<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        list.get(2);
    }

    @Test
    public void whenGetIteratorTwiceThenEveryFromBegin() {
        List<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);

        Iterator<Integer> first = list.iterator();
        assertThat(first.hasNext(), Is.is(true));
        assertThat(first.next(), Is.is(1));
        assertThat(first.hasNext(), Is.is(true));
        assertThat(first.next(), Is.is(2));
        assertThat(first.hasNext(), Is.is(false));

        Iterator<Integer> second = list.iterator();
        assertThat(second.hasNext(), Is.is(true));
        assertThat(second.next(), Is.is(1));
        assertThat(second.hasNext(), Is.is(true));
        assertThat(second.next(), Is.is(2));
        assertThat(second.hasNext(), Is.is(false));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenExpectedConcurrentModificationException() {
        List<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        Iterator<Integer> iterator = list.iterator();
        assertThat(iterator.hasNext(), Is.is(true));
        assertThat(iterator.next(), Is.is(1));
        list.add(3);
        assertThat(iterator.hasNext(), Is.is(true));
        iterator.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenExpectedNoSuchElementException() {
        List<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        Iterator<Integer> iterator = list.iterator();
        assertThat(iterator.hasNext(), Is.is(true));
        assertThat(iterator.next(), Is.is(1));
        iterator.next();
    }

    @Test
    public void whenListCreatedWithExistCollection() {
        List<Integer> list = new SimpleLinkedList<>(new ArrayList<>(Arrays.asList(1, 2, 3)));
        Iterator<Integer> iterator = list.iterator();
        assertThat(iterator.hasNext(), Is.is(true));
        assertThat(iterator.next(), Is.is(1));
        assertThat(iterator.hasNext(), Is.is(true));
        assertThat(iterator.next(), Is.is(2));
        assertThat(iterator.hasNext(), Is.is(true));
        assertThat(iterator.next(), Is.is(3));
        assertThat(iterator.hasNext(), Is.is(false));
    }
}
