package com.adrij.cache.memory;

import com.google.inject.Inject;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class MemoryFactory {
    private final Map<MemoryType, BaseMemory> MEMORY_TYPE_CLASS_MAP;

    @Inject
    public MemoryFactory(Map<MemoryType, BaseMemory> mp) {
        this.MEMORY_TYPE_CLASS_MAP = mp;
    }

    public BaseMemory getMemoryImp(MemoryType memoryType, int capacity) throws Exception {
        BaseMemory clazz = MEMORY_TYPE_CLASS_MAP.get(memoryType);
        if (clazz != null) {
            try {
                return clazz.getClass().getConstructor(int.class).newInstance(capacity);
            } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
                throw new Exception(e);
            }
        }
        return null;
    }
}
