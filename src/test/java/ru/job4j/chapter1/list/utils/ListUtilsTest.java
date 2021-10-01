package ru.job4j.chapter1.list.utils;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

public class ListUtilsTest {

    private List<Integer> input;

    @Before
    public void init() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    /**
     Tests for addBefore() function.
     */
    @Test
    public void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input, is(Arrays.asList(1, 2, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        ListUtils.addBefore(input, 3, 2);
    }

    /**
     Tests for addAfter() function.
     */
    @Test
    public void whenAddAfterLast() {
        ListUtils.addAfter(input, 1, 5);
        assertThat(input, is(Arrays.asList(1, 3, 5)));
    }

    @Test
    public void whenAddAfterFirst() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input, is(Arrays.asList(1, 2, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddAfterLastWithInvalidIndex() {
        ListUtils.addAfter(input, 5, 3);
    }

    /**
     Tests for removeIf() function
     */
    @Test
    public void whenRemoveLastValueMoreThan2() {
        ListUtils.removeIf(input, integer -> integer > 2);
        assertThat(input, is(Collections.singletonList(1)));
    }

    @Test
    public void whenRemoveFirstValueLessThan2() {
        ListUtils.removeIf(input, integer -> integer < 2);
        assertThat(input, is(Collections.singletonList(3)));
    }

    @Test
    public void whenRemoveAllEvenValues() {
        input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
        ListUtils.removeIf(input, integer -> integer % 2 == 0);
        assertThat(input, is(Arrays.asList(1, 3, 5, 7)));
    }

    @Test
    public void whenNoElementsForDeleting() {
        ListUtils.removeIf(input, integer -> integer > 25);
        assertThat(input, is(Arrays.asList(1, 3)));
    }

    /**
     Tests for replaceIf() function
     */
    @Test
    public void whenReplaceLastValueMoreThan2() {
        ListUtils.replaceIf(input, integer -> integer > 2, 7);
        assertThat(input, is(Arrays.asList(1, 7)));
    }

    @Test
    public void whenReplaceFirstValueLessThan2() {
        ListUtils.replaceIf(input, integer -> integer < 2, 7);
        assertThat(input, is(Arrays.asList(7, 3)));
    }

    @Test
    public void whenReplaceAllEvenValues() {
        input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
        ListUtils.replaceIf(input, integer -> integer % 2 == 0, 0);
        assertThat(input, is(Arrays.asList(1, 0, 3, 0, 5, 0, 7)));
    }

    @Test
    public void whenNoElementsForReplacing() {
        ListUtils.replaceIf(input, integer -> integer > 25, 0);
        assertThat(input, is(Arrays.asList(1, 3)));
    }

    /**
     Tests for removeAll() function
     */
    @Test
    public void whenRemoveAll() {
        input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
        ListUtils.removeAll(input, Arrays.asList(1, 2, 4, 7, 0));
        assertThat(input, is(Arrays.asList(3, 5, 6)));
    }

    @Test
    public void whenNoValuesForRemove() {
        input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
        ListUtils.removeAll(input, Arrays.asList(11, 22, 41, 70, 0));
        assertThat(input, is(Arrays.asList(1, 2, 3, 4, 5, 6, 7)));
    }
}