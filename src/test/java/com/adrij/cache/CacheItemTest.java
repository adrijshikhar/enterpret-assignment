package com.adrij.cache;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CacheItemTest {
    @Test
    void test_gettersSettersAndStats() {
        CacheItem cacheItem = new CacheItem("a", 1);
        cacheItem.incrementHitCount();
        cacheItem.incrementHitCount();
        assertEquals(cacheItem.getHitCount(), 2);

        assertEquals(cacheItem.getKey(), "a");
        cacheItem.setKey("b");
        assertEquals(cacheItem.getKey(), "b");

        assertEquals(cacheItem.getValue(), 1);
        cacheItem.setValue(2);
        assertEquals(cacheItem.getValue(), 2);

        CacheItem cacheItem2 = new CacheItem("d", 4);
        CacheItem cacheItem3 = new CacheItem("e", 5);

        cacheItem.setNext(cacheItem2);
        cacheItem.setPrev(cacheItem3);

        assertEquals(cacheItem.getNext(), cacheItem2);
        assertEquals(cacheItem.getPrev(), cacheItem3);
    }
}