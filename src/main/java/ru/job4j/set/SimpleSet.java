package ru.job4j.set;

import ru.job4j.list.SimpleArrayList;

import java.util.Iterator;
import java.util.Objects;

/**
 * Реализация коллекции SimpleSet на основе SimpleArrayList.
 * SimpleSet не хранит дубликаты.
 * @param <T> тип элементов множества.
 * @author Aleksandr Kuznetsov
 * @version 1.0
 */
public class SimpleSet<T> implements Set<T> {

    private SimpleArrayList<T> set = new SimpleArrayList<>(5);

    /**
     * Метод добавляет элемент в множество. Добавляет только те элементы
     * которых еще нет в этом множестве.
     * @param value добавляемый элемент.
     * @return true если элемента еще нет в этом множестве, иначе false.
     */
    @Override
    public boolean add(T value) {
        boolean rsl = !contains(value);
        if (rsl) {
            set.add(value);
        }
        return rsl;
    }

    /**
     * Метод проверяет, содержит ли множество переданный элемент.
     * @param value элемент, который проверяем.
     * @return true если содержит, иначе false.
     */
    @Override
    public boolean contains(T value) {
        boolean rsl = false;
        for (int i = 0; i < set.size(); i++) {
            if (Objects.equals(set.get(i), value)) {
                rsl = true;
                break;
            }
        }
        return rsl;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}
