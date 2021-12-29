package com.adrij.cache;

import com.adrij.cache.memory.MemoryFactory;
import com.adrij.cache.memory.MemoryRegistry;
import com.adrij.cache.memory.MemoryType;
import com.adrij.cache.policy.LRUPolicy;
import com.google.inject.Guice;
import com.google.inject.Injector;


public class LRUCache<K, V> extends LRUPolicy<K, V> {

    protected LRUCache(int capacity) throws Exception {
        super(capacity);
        Injector memoryInjector = Guice.createInjector(new MemoryRegistry());
        this.memoryImp = memoryInjector.getInstance(MemoryFactory.class).getMemoryImp(MemoryType.fromValue("in_memory"), capacity);
    }

}
