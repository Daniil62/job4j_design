package ru.job4j.chapter1.gc.references.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCache<K, V> {

    protected final Map<K, SoftReference<V>> cache = new HashMap<>();
    private static final String ERROR_MESSAGE = "Key should be not null and not empty.";

    public void put(K key, V value) {
        validate(key);
        cache.put(key, new SoftReference<>(value));
    }

    public V get(K key) {
        V result = cache.getOrDefault(key, new SoftReference<>(null)).get();
        if (result == null) {
            result = load(key);
            put(key, result);
        }
        return result;
    }

    private void validate(K key) {
        if (key == null || String.valueOf(key).isEmpty()) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
    }

    public abstract V load(K key);
}