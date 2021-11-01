package ru.job4j.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Реализация односвязного списка.
 * @param <T> тип данных односвязного списка.
 * @author Aleksandr Kuznetsov
 * @version 1.0
 */
public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;

    /**
     * Метод добавляет узел в конец односвязного списка.
     * @param value значение узла.
     */
    public void add(T value) {
        Node<T> node = new Node<T>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    /**
     * Метод добавляет узел в начало односвязного списка.
     * @param value значение узла.
     */
    public void addFirst(T value) {
        head = new Node<T>(value, head);
    }

    /**
     * Метод удаляет первый узел списка.
     * @return удаленное значение.
     */
    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        Node<T> node = head;
        head = node.next;
        node.next = null;
        return node.value;
    }

    /**
     * Метод разворачивает односвязный список.
     * Суть алгоритма заключается в установлении текущему ссылки
     * на следующий в качестве предыдущего. Далее мы просто обновляем
     * значения текущего и предыдущего. Наконец, предыдущий ставится головой списка.
     * @return true если список можно перевернуть.
     */
    public boolean revert() {
        boolean rsl = false;
        if (head != null && head.next != null) {
            Node<T> previous = null;
            Node<T> current = head;
            while (current != null) {
                Node<T> next = current.next;
                current.next = previous;
                previous = current;
                current = next;
            }
            head = previous;
            rsl = true;
        }
        return rsl;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        private T value;
        private Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}
