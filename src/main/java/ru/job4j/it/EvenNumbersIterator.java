package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Итератор, который возвращает только четные числа.
 * @author Aleksandr Kuznetsov
 * @version 1.0
 */
public class EvenNumbersIterator implements Iterator<Integer> {

    private int[] data;
    private int index;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        for (int i = index; i < data.length; i++) {
            if (data[i] % 2 == 0) {
                return true;
            }
         }
        return false;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int rsl = 0;
        for (int i = index; i < data.length; i++) {
            if (data[i] % 2 == 0) {
                rsl = data[i];
                index = ++i;
                break;
            }
        }
        return rsl;
    }

}