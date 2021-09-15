package ru.job4j.data_structures_and_algorithms;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import java.util.Collections;
import java.util.List;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class FlatMapTest {
     @Test
    public void whenDiffNext() {
         Iterator<Iterator<Integer>> data = List.of(
                 List.of(1).iterator(),
                 List.of(2, 3).iterator()
         ).iterator();
         FlatMap<Integer> flatMap = new FlatMap<>(data);
         assertThat(flatMap.next(), is(1));
         assertThat(flatMap.next(), is(2));
         assertThat(flatMap.next(), is(3));
     }

     @Test
    public void whenSeqNext() {
         Iterator<Iterator<Integer>> data = List.of(List.of(1, 2, 3).iterator()).iterator();
         FlatMap<Integer> flatMap = new FlatMap<>(data);
         assertThat(flatMap.next(), is(1));
         assertThat(flatMap.next(), is(2));
         assertThat(flatMap.next(), is(3));
     }

     @Test
     public void whenMultiHasNext() {
         Iterator<Iterator<Integer>> data = List.of(List.of(1).iterator()).iterator();
         FlatMap<Integer> flatMap = new FlatMap<>(data);
         assertThat(flatMap.hasNext(), is(true));
         assertThat(flatMap.hasNext(), is(true));
     }

    @Test
    public void whenHasNextFalse() {
        Iterator<Iterator<Integer>> data = List.of(List.of(1).iterator()).iterator();
        FlatMap<Integer> flatMap = new FlatMap<>(data);
        assertThat(flatMap.next(), is(1));
        assertThat(flatMap.hasNext(), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenEmpty() {
        Iterator<Iterator<Object>> data = List.of(Collections.emptyIterator()).iterator();
        FlatMap<Object> flatMap = new FlatMap<>(data);
        flatMap.next();
    }

    @Test
    public void whenSeveralEmptyAndNoEmpty() {
        Iterator<Iterator<?>> it = List.of(
                Collections.emptyIterator(),
                Collections.emptyIterator(),
                Collections.emptyIterator(),
                List.of(1).iterator()
        ).iterator();
        FlatMap flatMap = new FlatMap(it);
        assertTrue(flatMap.hasNext());
        assertThat(1, is(flatMap.next()));
    }

    @Test
    public void whenSeveralEmptyThenReturnFalse() {
        Iterator<Iterator<Object>> it = List.of(
                Collections.emptyIterator(),
                Collections.emptyIterator(),
                Collections.emptyIterator(),
                Collections.emptyIterator()
        ).iterator();
        FlatMap<Object> flatMap = new FlatMap<>(it);
        assertFalse(flatMap.hasNext());
    }
}
