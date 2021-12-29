package com.adrij.cache.policy;

public interface Policy<K, V> {
    void put(K key, V value);

    V get(K key);

    int getSize();

    boolean isEmpty();

    void clear();
}
