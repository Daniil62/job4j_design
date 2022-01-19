package ru.job4j.chapter1.ood.kiss;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;

public class MaxMinTest {

    private List<Integer> list;
    private MaxMin maxMin;
    private IntegerComparator comparator;

    @Before
    public void init() {
        list = List.of(3, 42, 5, 89, 50, 4, 61, 1, 14, 3, 15);
        maxMin = new MaxMin();
        comparator = new IntegerComparator();
    }

    @Test
    public void whenMinExpectedValueIs1() {
        Assert.assertThat(maxMin.min(list, comparator), is(1));
    }

    @Test
    public void whenMaxExpectedValueIs89() {
        Assert.assertThat(maxMin.max(list, comparator), is(89));
    }
}
