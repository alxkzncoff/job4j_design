package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Реализация простого словаря.
 * @param <K> ключ
 * @param <V> значение
 * @author Aleksandr Kuznetsov
 * @version 1.0
 */
public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    /**
     * Метод добавляет пару ключ/значение в словарь. Без решения коллизий.
     * Если отсутствует место, то пара не добавляется в словарь.
     * @param key ключ
     * @param value значение
     * @return true если пара успешно добавлена, иначе false.
     */
    @Override
    public boolean put(K key, V value) {
        boolean rsl = false;
        if (count >= LOAD_FACTOR * capacity) {
            expand();
        }
        int hash = (key == null) ? 0 : hash(key.hashCode());
        int index = indexFor(hash);
        MapEntry<K, V> pair = new MapEntry<>(key, value);
        if (table[index] == null) {
            table[index] = pair;
            modCount++;
            count++;
            rsl = true;
        }
        return rsl;
    }

    /**
     * Метод вычисляет хэш ключа.
     * @param hashcode хэш-код.
     * @return int.
     */
    private int hash(int hashcode) {
        return (hashcode) ^ (hashcode >>> 16);
    }

    /**
     * Метод вычисляет индекс, по которому нужно разместить пару ключ/значение
     * во внутренней таблице словаря.
     * @param hash хэш.
     * @return индекс.
     */
    private int indexFor(int hash) {
        return hash % capacity;
    }

    /**
     * Метод увеличивает размер внутренней таблицы словаря в два раза.
     */
    private void expand() {
        capacity *= 2;
        int hash;
        int index;
        MapEntry<K, V>[] temp = new MapEntry[capacity];
        for (MapEntry<K, V> kvMapEntry : table) {
            hash = (kvMapEntry.key == null) ? 0 : hash(kvMapEntry.key.hashCode());
            index = indexFor(hash);
            temp[index] = kvMapEntry;
        }
        table = temp;
    }

    /**
     * Метод возвращает значение, которое соответствует указанному ключу, иначе
     * возвращает null.
     * @param key ключ
     * @return значение соответствующее ключу или null.
     */
    @Override
    public V get(K key) {
        V value = null;
        int hash = (key == null) ? 0 : hash(key.hashCode());
        int index = indexFor(hash);
        if (table[index] != null) {
            K tableKey = table[index].key;
            value = tableKey.equals(key) ? table[index].value : null;
        }
        return value;
    }

    /**
     * Метод удаляет пару ключ/значение.
     * @param key ключ.
     * @return true, если пара успешно удалена, иначе false.
     */
    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        if (get(key) != null) {
            int hash = (key == null) ? 0 : hash(key.hashCode());
            int index = indexFor(hash);
            table[index] = null;
            count--;
            modCount++;
            rsl = true;
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            private final int expectedModCount = modCount;

            private int point = 0;
            private int index = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return point < count;
            }

            @Override
            public K next() {
                K rsl = null;
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                for (; index < table.length; index++) {
                    if (table[index] != null) {
                        rsl = table[index].key;
                        point++;
                        index++;
                        break;
                    }
                }
                return rsl;
            }

        };
    }

    private static class MapEntry<K, V> {

        private final K key;
        private final V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }
}
