package ru.job4j.tree;

import java.util.*;
import java.util.function.Predicate;

/**
 * Элементарная структура данных - дерево.
 * @param <E> элементы дерева.
 * @author Aleksandr Kuznetsov.
 * @version 1.0
 */
public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    /**
     * Метод добавляет новый элемент в дерево.
     * @param parent родительский узел.
     * @param child потомки.
     * @return true, если элемент добавлен, иначе false.
     */
    @Override
    public boolean add(E parent, E child) {
        Optional<Node<E>> parentNode = findBy(parent);
        boolean rsl = parentNode.isPresent() && findBy(child).isEmpty();
        if (rsl) {
            parentNode.get().getChildren().add(new Node<>(child));
        }
        return rsl;
    }

    /**
     * Метод ищет элемент дерева по значению.
     * @param value значение для поиска.
     * @return Optional элемент дерева.
     */
    @Override
    public Optional<Node<E>> findBy(E value) {
        return findByPredicate(el -> Objects.equals(el.getValue(), value));
    }

    /**
     * Метод проверяет бинарное дерево или нет.
     * @return true или false.
     */
    @Override
    public boolean isBinary() {
        return findByPredicate(el -> el.getChildren().size() > 2).isEmpty();
    }

    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (condition.test(el)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.getChildren());
        }
        return rsl;
    }
}