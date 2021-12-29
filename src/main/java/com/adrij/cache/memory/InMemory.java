package com.adrij.cache.memory;

import com.adrij.cache.CacheItem;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class InMemory<K, V> extends BaseMemory<K, V> {
    private ConcurrentMap<Object, CacheItem<K, V>> map;

    public InMemory() {
    }

    public InMemory(int capacity) {
        super(capacity);
        map = new ConcurrentHashMap<>(capacity);
    }

    @Override
    public CacheItem<K, V> get(Object key) {
        return map.get(key);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public void remove(Object key) {
        map.remove(key);
    }

    @Override
    public void put(Object key, CacheItem node) {
        map.put(key, (CacheItem<K, V>) node);
    }

    @Override
    public int size() {
        return map.size();
    }
}
