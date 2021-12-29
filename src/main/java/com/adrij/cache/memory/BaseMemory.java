package com.adrij.cache.memory;

public abstract class BaseMemory<K, V> implements MemoryInterface<K,V> {
    public int CAPACITY;

    public BaseMemory() {
    }

    public BaseMemory(int capacity) {
        this.CAPACITY = capacity;
    }
}
