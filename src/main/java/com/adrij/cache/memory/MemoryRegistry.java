package com.adrij.cache.memory;
import com.google.inject.AbstractModule;
import com.google.inject.multibindings.MapBinder;

public class MemoryRegistry extends AbstractModule {
    @Override
    protected void configure() {
        MapBinder<MemoryType, BaseMemory> mapbinder
                = MapBinder.newMapBinder(binder(), MemoryType.class, BaseMemory.class);

        mapbinder.addBinding(MemoryType.IN_MEMORY).to(InMemory.class);
    }
}

