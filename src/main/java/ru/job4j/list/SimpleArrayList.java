package ru.job4j.list;

import java.util.*;

/**
 * реализация списка на основе динамического массива.
 * @param <T> тип данных списка.
 * @author Aleksandr Kuznetsov
 * @version 1.0
 */
public class SimpleArrayList<T> implements List<T> {

    private T[] container;

    private int size;

    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    /**
     * Метод увеличивает размер списка в два раза.
     * @return новый список.
     */
    public T[] grow() {
        return Arrays.copyOf(container, container.length * 2);
    }

    /**
     * Метод добавляет в конце списка новый элемент.
     * @param value новый элемент.
     */
    @Override
    public void add(T value) {
        modCount++;
        if (size == container.length) {
            container = grow();
        }
        container[size] = value;
        size++;
    }

    /**
     * Метод делает валидацию индекса и возвращает элемент на указанной позиции.
     * @param index позиция элемента, который нужно вернуть.
     * @return найденный элемент.
     */
    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    /**
     * Метод заменяет элемент на указанной позиции на новый элемент.
     * @param index позиция элемента, который нужно заменить.
     * @param newValue новый элемент.
     * @return старый элемент.
     */
    @Override
    public T set(int index, T newValue) {
        T oldValue = get(index);
        container[index] = newValue;
        return oldValue;
    }

    /**
     * Метод удаляет элемент по указанной позиции.
     * @param index позиция элемента, который нужно удалить.
     * @return удаленный элемент.
     */
    @Override
    public T remove(int index) {
        T removeValue = get(index);
        modCount++;
        System.arraycopy(container, index + 1, container, index, container.length - index - 1);
        container[container.length - 1] = null;
        size--;
        return removeValue;
    }

    /**
     * Метод возвращает количество элементов в списке.
     * @return количество элементов.
     */
    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private final int expectedModCount = modCount;

            private int point = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return point < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[point++];
            }

        };
    }
}