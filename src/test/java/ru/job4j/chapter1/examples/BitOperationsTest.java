package ru.job4j.chapter1.examples;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class BitOperationsTest {

    private BitOperations operations;

    @Before
    public void init() {
        operations = new BitOperations();
    }

    @Test
    public void whenDecValueConvertedToBin() {
        int value = 6;
        assertThat(operations.convertDecValueToBin(value), is("011"));
    }

    @Test
    public void whenBinValueConvertedToDec() {
        String value = "011";
        assertThat(operations.convertBinValueToDec(value), is(6));
    }

    @Test
    public void whenValueMoveTo2StepsLeftThenConvertToDec() {
        int value = 7;
        String expected = "00111";
        assertThat(operations.moveLeft(value, 2), is(expected));
        assertThat(operations.convertBinValueToDec(expected), is(28));
    }

    @Test
    public void whenValueMoveTo2StepsRightThenConvertToDec() {
        int value = 14;
        String expected = "11";
        assertThat(operations.moveRight(value, 2), is(expected));
        assertThat(operations.convertBinValueToDec(expected), is(3));
    }

    @Test
    public void whenValuesCompareByXorThenResultConvertToDec() {
        int value1 = 12;
        int value2 = 10;
        String expected = "011";
        assertThat(operations.convertDecValueToBin(value1), is("0011"));
        assertThat(operations.convertDecValueToBin(value2), is("0101"));
        assertThat(operations.compareByXOR(value1, value2), is(expected));
        assertThat(operations.convertBinValueToDec(expected), is(6));
    }

    @Test
    public void whenValuesCompareByBitwiseAndThenResultConvertToDec() {
        int value1 = 12;
        int value2 = 10;
        String expected = "0001";
        assertThat(operations.compareByBitwiseAnd(value1, value2), is(expected));
        assertThat(operations.convertBinValueToDec(expected), is(8));
    }

    @Test
    public void whenValuesCompareByBitwiseOrThenResultConvertToDec() {
        int value1 = 12;
        int value2 = 10;
        String expected = "0111";
        assertThat(operations.compareByBitwiseOr(value1, value2), is(expected));
        assertThat(operations.convertBinValueToDec(expected), is(14));
    }
}
