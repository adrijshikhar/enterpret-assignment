package com.adrij.cache;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LFUCacheTest {

    @Test
    void reorder() throws Exception {
        LFUCache<String, Integer> cache = new LFUCache<String, Integer>(5);

        cache.put("a", 1);
        cache.put("b", 2);
        cache.put("c", 3);
        cache.put("d", 4);
        cache.put("e", 5);

        cache.get("a");
        cache.get("a");
        cache.get("a");
        cache.get("b");
        cache.get("b");
        cache.get("d");
        cache.get("d");
        cache.get("e");
        cache.get("c");
        cache.get("e");
        cache.get("e");
        cache.get("e");

        CacheItem<String, Integer> leaseFreqElem = cache.memoryImp.get("c");

        assertEquals(leaseFreqElem.getNext().getValue(), 2);
        assertEquals(leaseFreqElem.getNext().getNext().getValue(), 4);
        assertEquals(leaseFreqElem.getNext().getNext().getNext().getValue(), 1);
        assertEquals(leaseFreqElem.getNext().getNext().getNext().getNext().getValue(), 5);
    }

    @Test
    void put() throws Exception {
        LFUCache<String, Integer> cache = new LFUCache<>(5);

        cache.put("a", 1);
        cache.put("b", 2);
        cache.put("c", 3);
        cache.put("d", 4);
        cache.put("e", 5);

        cache.get("a");
        cache.get("a");
        cache.get("a");
        cache.get("b");
        cache.get("b");
        cache.get("d");
        cache.get("d");
        cache.get("e");
        cache.get("c");
        cache.get("e");
        cache.get("e");
        cache.get("e");

        assertEquals(cache.memoryImp.size(), 5);
        assertEquals((cache.memoryImp.get("a")).getValue(), 1);

        cache.put("f", 6);

        assertEquals(cache.memoryImp.size(), 5);
        assertNull(cache.memoryImp.get("c"));
    }

    @Test
    void get() throws Exception {
        LFUCache<String, Integer> cache = new LFUCache<String, Integer>(5);
        cache.put("a", 1);

        assertEquals(cache.get("a"), 1);
        assertNull(cache.get("b"));
    }
}