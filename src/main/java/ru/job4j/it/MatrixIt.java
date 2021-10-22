package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Итератор которые последовательно возвращает элементы в обратном порядке.
 * @author Aleksandr Kuznetsov
 * @version 1.0
 */
public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        while (column == data[row].length) {
            if (row + 1 == data.length) {
                break;
            }
            row++;
            column = 0;
        }
        return data[row].length != 0;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
    return data[row][column++];
    }
}