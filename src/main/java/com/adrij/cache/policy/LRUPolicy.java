package com.adrij.cache.policy;

import com.adrij.cache.CacheItem;
import com.adrij.cache.memory.BaseMemory;

public class LRUPolicy<K, V> extends BasePolicy<K, V> {
    public BaseMemory memoryImp;

    public LRUPolicy(int capacity) {
        super(capacity);
    }

    private synchronized void reorder(CacheItem<K, V> node) {
        if (last == node) {
            return;
        }
        if (first == node) {
            first = node.getNext();
        } else {
            node.getPrev().setNext(node.getNext());
        }
        last.setNext(node);
        node.setPrev(last);
        node.setNext(null);
        last = node;
    }

    @Override
    public void put(K key, V value) {
        CacheItem<K, V> node = new CacheItem<>(key, value);

        if (!memoryImp.containsKey(key)) {
            if (getSize() >= CAPACITY) {
                deleteNode(first);
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
        reorder(node);
        return (V) node.getValue();
    }

    @Override
    public int getSize() {
        return size;
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
