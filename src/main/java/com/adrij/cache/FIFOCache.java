package com.adrij.cache;

import com.adrij.cache.memory.MemoryFactory;
import com.adrij.cache.memory.MemoryRegistry;
import com.adrij.cache.memory.MemoryType;
import com.adrij.cache.policy.BasePolicy;
import com.adrij.cache.policy.FIFOPolicy;
import com.google.inject.Guice;
import com.google.inject.Injector;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class FIFOCache<K, V> extends FIFOPolicy<K, V> {

    protected FIFOCache(int capacity) throws Exception {
        super(capacity);
        Injector memoryInjector = Guice.createInjector(new MemoryRegistry());
        this.memoryImp = memoryInjector.getInstance(MemoryFactory.class).getMemoryImp(MemoryType.fromValue("in_memory"), capacity);
    }

}
