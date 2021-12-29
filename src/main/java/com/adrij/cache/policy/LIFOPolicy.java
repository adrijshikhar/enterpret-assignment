package com.adrij.cache.policy;

import com.adrij.cache.CacheItem;

public class LIFOPolicy<K, V> extends BasePolicy<K, V> {
    public LIFOPolicy(int capacity) {
        super(capacity);
    }

    @Override
    public void put(K key, V value) {
        CacheItem<K, V> node = new CacheItem<K, V>(key, value);

        if (!memoryImp.containsKey(key)) {
            if (getSize() >= CAPACITY) {
                deleteNode(last);
            }
            addNodeToLast(node);
        }
        memoryImp.put(key, node);
    }

    @Override
    public V get(K key) {
        if (!memoryImp.containsKey(key)) {
            return null;
        }
        CacheItem<K, V> node = memoryImp.get(key);
        node.incrementHitCount();
        return (V) node.getValue();
    }

    @Override
    public void clear() {
        memoryImp.clear();
    }

    protected synchronized void deleteNode(CacheItem<K, V> node) {
        if (node == null) {
            return;
        }
        if (last == node) {
            last = node.getPrev();
        }
        if (first == node) {
            first = node.getNext();
        }
        memoryImp.remove(node.getKey());
        size--;
    }
}
