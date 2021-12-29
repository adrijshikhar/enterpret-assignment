package com.adrij.cache;

public class CacheItem<K, V> {
    private K key;
    private V value;
    private int hitCount = 0;
    private CacheItem<K, V> prev, next;

    public CacheItem(K key, V value) {
        this.value = value;
        this.key = key;
    }

    public synchronized void incrementHitCount() {
        this.hitCount++;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public int getHitCount() {
        return hitCount;
    }

    public CacheItem<K, V> getPrev() {
        return prev;
    }

    public void setPrev(CacheItem<K, V> prev) {
        this.prev = prev;
    }

    public CacheItem<K, V> getNext() {
        return next;
    }

    public void setNext(CacheItem<K, V> next) {
        this.next = next;
    }
}