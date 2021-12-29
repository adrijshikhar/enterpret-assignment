package com.adrij.cache.policy;

import com.adrij.cache.CacheItem;
import com.adrij.cache.memory.BaseMemory;

public class LFUPolicy<K, V> extends BasePolicy<K, V> {
    public BaseMemory memoryImp;

    public LFUPolicy(int capacity) {
        super(capacity);
    }

    synchronized void reorder(CacheItem<K, V> node) {
        if (last == node) {
            return;
        }
        CacheItem<K, V> nextNode = node.getNext();
        while (nextNode != null) {
            if (nextNode.getHitCount() > node.getHitCount()) {
                break;
            }
            if (first == node) {
                first = nextNode;
            }
            if (node.getPrev() != null) {
                node.getPrev().setNext(nextNode);
            }
            nextNode.setPrev(node.getPrev());
            node.setPrev(nextNode);
            node.setNext(nextNode.getNext());
            if (nextNode.getNext() != null) {
                nextNode.getNext().setPrev(node);
            }
            nextNode.setNext(node);
            nextNode = node.getNext();
        }
        if (node.getNext() == null) {
            last = node;
        }
    }

    @Override
    public void put(K key, V value) {
        CacheItem<K, V> node = new CacheItem<>(key, value);

        if (!memoryImp.containsKey(key)) {
            if (getSize() >= CAPACITY) {
                deleteNode(first);
            }
            addNodeToFirst(node);
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
