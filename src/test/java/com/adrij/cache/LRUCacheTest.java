package com.adrij.cache;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;

class LRUCacheTest {
    @Test
    void put() throws Exception {
        LRUCache<String, Integer> cache = new LRUCache<String, Integer>(5);

        cache.put("a", 1);
        cache.put("b", 2);
        cache.put("c", 3);
        cache.put("d", 4);
        cache.put("e", 5);

        cache.get("a");
        cache.get("c");
        cache.get("d");
        cache.get("e");

        assertEquals(cache.memoryImp.size(), 5);
        assertEquals(1,1);
        assertEquals((cache.memoryImp.get("a")).getValue(), 1);

        cache.put("f", 6);

        assertEquals(cache.memoryImp.size(), 5);
        assertNull(cache.memoryImp.get("b"));
    }

    @Test
    void get() throws Exception {
        LRUCache<String, Integer> cache = new LRUCache<String, Integer>(5);
        cache.put("a", 1);

        assertEquals(cache.get("a"), 1);
        assertNull(cache.get("b"));
    }
}