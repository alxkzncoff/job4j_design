package ru.job4j.it;

import static org.hamcrest.Matchers.is;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.Assert.assertThat;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(input, is(Arrays.asList(1, 2, 3)));
    }

    @Test
    public void whenAddBeforeFirst() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2));
        ListUtils.addBefore(input, 0, 0);
        assertThat(input, is(Arrays.asList(0, 1, 2)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfter() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 3));
        ListUtils.addAfter(input, 1, 2);
        assertThat(input, is(Arrays.asList(0, 1, 2, 3)));
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertThat(input, is(Arrays.asList(0, 1, 2, 3)));
    }

    @Test
    public void whenRemoveIf() {
        List<Integer> input = new ArrayList<>(Arrays.asList(2, 1, 2, 3, 2, 5));
        Predicate<Integer> filter = value -> value == 2;
        ListUtils.removeIf(input, filter);
        assertThat(input, is(Arrays.asList(1, 3, 5)));
    }

    @Test
    public void whenReplaceIf() {
        List<Integer> input = new ArrayList<>(Arrays.asList(4, 1, 4, 3, 4, 5, 4));
        Predicate<Integer> filter = value -> value == 4;
        ListUtils.replaceIf(input, filter, 9);
        assertThat(input, is(Arrays.asList(9, 1, 9, 3, 9, 5, 9)));
    }

    @Test
    public void whenReplaceAll() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 6, 3, 4, 5, 9));
        List<Integer> elements = new ArrayList<>(Arrays.asList(0, 6, 4, 9));
        ListUtils.removeAll(input, elements);
        assertThat(input, is(Arrays.asList(1, 3, 5)));
    }
}