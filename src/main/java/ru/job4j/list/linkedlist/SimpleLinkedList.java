package ru.job4j.list.linkedlist;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Реализация связного списока.
 * @param <E>
 * @author Aleksandr Kuznetsov
 * @version 1.0
 */
public class SimpleLinkedList<E> implements List<E> {
    private Node<E> first;

    private Node<E> last;

    private int size = 0;

    private int modCount;

    private static class Node<E> {
        private E item;
        private Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }

        public E getItem() {
            return item;
        }

        public Node<E> getNext() {
            return next;
        }
    }

    /**
     * Метод добавляет новый узел в конец списка.
     * @param value значение нового узла.
     */
    @Override
    public void add(E value) {
        Node<E> newNode = new Node<>(value, null);
        if (first == null) {
            first = newNode;
        } else {
            last.next = newNode;
        }
        last = newNode;
        size++;
        modCount++;
    }

    /**
     * Метод перебирает узлы до указаного индекса и возвращает значение узла.
     * @param index индекс узла.
     * @return значение узла.
     */
    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> node = first;
        for (int i = 0; i < index; i++) {
            node = node.getNext();
        }
        return node.getItem();
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private final int expectedModCount = modCount;

            private Node<E> point = first;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return point != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E value = point.getItem();
                point = point.getNext();
                return value;
            }
        };
    }
}
