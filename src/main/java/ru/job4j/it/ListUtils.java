package ru.job4j.it;

import java.util.*;
import java.util.function.Predicate;

/**
 * Реализация списка с ListIterator. Дает возможность менять список
 * во время итерирования.
 * @author Aleksandr Kuznetsov
 * @version 1.0
 */
public class ListUtils {
    /**
     * Метод вставляет новый элемент перед индексом.
     * @param list список в который нужно вставить новый элемент.
     * @param index индекс элемента, перед которым нужно вставить новый элемент.
     * @param value элемент, которое нужно вставить.
     * @param <T> тип элемента.
     */
    public static <T> void addBefore(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> iterator = list.listIterator(index);
        iterator.add(value);
    }

    /**
     * Метод вставляет новый элемент после индекса.
     * @param list список в который нужно вставить новый элемент.
     * @param index индекс элемента, после которым нужно вставить новый элемент.
     * @param value элемент, которое нужно вставить.
     * @param <T> тип элемента.
     */
    public static <T> void addAfter(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> iterator = list.listIterator(index + 1);
        iterator.add(value);
    }

    /**
     * Метод удаляет все элементы, которые удовлетворяют предикату.
     * @param list список из которого нужно удалить элементы.
     * @param filter фильтр на основе которого удаляем элементы.
     * @param <T> тип элементов.
     */
    public static <T> void removeIf(List<T> list, Predicate<T> filter) {
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (filter.test(iterator.next())) {
                iterator.remove();
            }
        }
    }

    /**
     * Метод заменяет все элементы, которые удовлетворяют предикату.
     * @param list список в котором заменяем элементы.
     * @param filter фильтр на основе которого меняем элементы.
     * @param value элемент на который заменяем.
     * @param <T> тип элементов.
     */
    public static <T> void replaceIf(List<T> list, Predicate<T> filter, T value) {
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (filter.test(iterator.next())) {
                iterator.set(value);
            }
        }
    }

    /**
     * Метод удаляет из списка те элементы, которые есть в списке elements.
     * @param list список из которого удаляем элементы.
     * @param elements список на основе которого удаляем элементы.
     * @param <T> тип элементов.
     */
    public static <T> void removeAll(List<T> list, List<T> elements) {
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (elements.contains(iterator.next())) {
                iterator.remove();
            }
        }
    }

}
