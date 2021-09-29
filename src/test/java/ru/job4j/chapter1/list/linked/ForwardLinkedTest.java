package ru.job4j.chapter1.list.linked;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ForwardLinkedTest {

    @Test(expected = NoSuchElementException.class)
    public void whenDeleteFirst() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(1);
        linked.deleteFirst();
        linked.iterator().next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenDeleteEmptyLinked() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.deleteFirst();
    }

    @Test
    public void whenMultiDelete() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(1);
        linked.add(2);
        assertThat(linked.deleteFirst(), is(1));
        Iterator<Integer> it = linked.iterator();
        assertThat(it.next(), is(2));
    }

    @Test
    public void whenAddFirst() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(1);
        linked.add(2);
        linked.addFirst(0);
        Iterator<Integer> it = linked.iterator();
        assertThat(it.next(), is(0));
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
    }

    @Test
    public void checkSizeBeforeAndAfterAddingAndDeleting() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        assertThat(linked.size(), is(0));
        linked.add(1);
        linked.add(2);
        assertThat(linked.size(), is(2));
        linked.addFirst(7);
        assertThat(linked.size(), is(3));
        linked.deleteFirst();
        linked.deleteFirst();
        assertThat(linked.size(), is(1));
    }
}
