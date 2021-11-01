package ru.job4j.list;

/**
 * Реализация структуры данных Stack.
 * @param <T> тип данных стека.
 * @author Aleksandr Kuzntsov
 * @version 1.0
 */
public class SimpleStack<T> {

    private final ForwardLinked<T> linked = new ForwardLinked<T>();

    /**
     * Метод возвращает значение из стека положенное туда последним и удаляет его.
     * @return найденное значение.
     */
    public T pop() {
        return linked.deleteFirst();
    }

    /**
     * Метод добавляет значение в стек.
     * @param value добавляемое значение.
     */
    public void push(T value) {
        linked.addFirst(value);
    }
}
