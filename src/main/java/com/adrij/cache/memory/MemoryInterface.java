package com.adrij.cache.memory;

import com.adrij.cache.CacheItem;

public interface MemoryInterface<K, V> {
    CacheItem<K, V> get(K key);

    void clear();

    boolean containsKey(K key);

    void remove(K key);

    void put(K key, CacheItem<K, V> node);

    int size();

}
