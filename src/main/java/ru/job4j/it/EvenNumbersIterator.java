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
        boolean rsl = false;
        for (; index < data.length; index++) {
            if (data[index] % 2 == 0) {
                rsl = true;
                break;
            }
         }
        return rsl;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[index++];
    }

}