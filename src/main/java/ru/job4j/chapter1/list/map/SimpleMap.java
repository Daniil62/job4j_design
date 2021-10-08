package ru.job4j.chapter1.list.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;
    private int capacity;
    private int count = 0;
    private int modCount = 0;
    private MapEntry<K, V>[] table;

    public SimpleMap(int capacity) {
        this.capacity = capacity;
        table = new MapEntry[capacity];
    }

    public SimpleMap() {
        this.capacity = 8;
        table = new MapEntry[capacity];
    }

    @Override
    public boolean put(K key, V value) {
        if ((float) count / capacity > LOAD_FACTOR) {
            grow();
        }
        int index = hash(key);
        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            count++;
            modCount++;
            return true;
        }
        return false;
    }

    @Override
    public V get(K key) {
        int index = hash(key);
        MapEntry<K, V> e = table[index];
        if (e == null || !(key == null || e.key.hashCode() == key.hashCode() && e.key.equals(key))) {
            return null;
        }
        return e.value;
    }

    @Override
    public boolean remove(K key) {
        int index = hash(key);
        MapEntry<K, V> e = table[index];
        if (e == null || (e.key.hashCode() != key.hashCode() && !e.key.equals(key))) {
            return false;
        }
        table[index] = null;
        --count;
        ++modCount;
        return true;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            int point = 0;
            int expectedModeCount = modCount;

            @Override
            public boolean hasNext() {
                while (point < capacity) {
                    if (table[point] != null) {
                        return true;
                    }
                    point++;
                }
                return false;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModeCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return table[point++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private int hash(K key) {
        return key == null ? 0 : key.hashCode() & capacity - 1;
    }

    private void grow() {
        MapEntry<K, V>[] tempArray = table;
        capacity *= 2;
        table = new MapEntry[capacity];
        for (MapEntry<K, V> e : tempArray) {
            if (e != null) {
                table[hash(e.key)] = new MapEntry<>(e.key, e.value);
            }
        }
    }

    public int size() {
        return count;
    }
}
