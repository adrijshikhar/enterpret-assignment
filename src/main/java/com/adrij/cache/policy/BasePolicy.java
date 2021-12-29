package com.adrij.cache.policy;

import com.adrij.cache.CacheItem;
import com.adrij.cache.memory.BaseMemory;

public abstract class BasePolicy<K, V> implements Policy<K, V> {
    protected int CAPACITY;
    protected CacheItem<K, V> first;
    protected CacheItem<K, V> last;
    protected int size;

    public BaseMemory memoryImp;

    public BasePolicy(int capacity) {
        this.CAPACITY = capacity;
    }

    protected synchronized void addNodeToLast(CacheItem<K, V> node) {
        if (last != null) {
            last.setNext(node);
            node.setPrev(last);
        }

        last = node;
        if (first == null) {
            first = node;
        }
        size++;
    }

    protected synchronized void addNodeToFirst(CacheItem<K, V> node) {
        if (first != null) {
            node.setNext(first);
            first.setPrev(node);
        }

        first = node;
        if (last == null) {
            last = node;
        }
        size++;
    }

    @Override
    public boolean isEmpty() {
        return getSize() == 0;
    }

    @Override
    public int getSize() {
        return size;
    }


    public abstract void put(K key, V value);

}
