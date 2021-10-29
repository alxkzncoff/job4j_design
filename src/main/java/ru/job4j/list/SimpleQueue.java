package ru.job4j.list;

import java.util.Iterator;

/**
 * Реализация очереди с помощью двух стеков.
 * @param <T> тип данных очереди.
 * @author Aleksandr Kuznetcov
 * @version 1.0
 */
public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    private int inSize;
    private int outSize;

    /**
     * Метод возвращает значение в порядке очереди (FIFO).
     * Для этого значение из стека in перекладываются в стек out,
     * для того, чтобы первое положенное значение в стек in оказалось
     * в начале стека out.
     * @return первое значение в очереди.
     */
    public T poll() {
        if (outSize == 0) {
            while (inSize > 0) {
                out.push(in.pop());
                inSize--;
                outSize++;
            }
        }
        outSize--;
        return out.pop();
    }

    /**
     * Метод добавляет значение в очеред.
     * @param value добавляемое значение.
     */
    public void push(T value) {
        in.push(value);
        inSize++;
    }
}
