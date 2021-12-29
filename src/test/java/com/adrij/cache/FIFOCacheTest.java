package com.adrij.cache;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FIFOCacheTest {
    @Test
    void put() throws Exception {
        FIFOCache<String, Integer> cache = new FIFOCache<>(5);

        cache.put("a", 1);
        cache.put("b", 2);
        cache.put("c", 3);
        cache.put("d", 4);
        cache.put("e", 5);

        assertEquals(cache.memoryImp.size(), 5);
        assertEquals((cache.memoryImp.get("a")).getValue(), 1);

        cache.put("f", 6);

        assertEquals(cache.memoryImp.size(), 5);
        assertNull(cache.memoryImp.get("a"));

        cache.put("c", 7);
        assertNotNull(cache.memoryImp.get("b"));
    }

    @Test
    void get() throws Exception {
        FIFOCache<String, Integer> cache = new FIFOCache<>(5);
        cache.put("a", 1);

        assertEquals(cache.get("a"), 1);
        assertNull(cache.get("b"));
    }
}