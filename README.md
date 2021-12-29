# Enterpret Assignment

## Architecture
In this library, memory type is injected into policies using Guice. 
Interface for memory type and policy type have concrete implementation using interfaces.
Then `Base` classes are abstract classes which implement the defined structure.
Policies are extended from the `BasePolicy` and memory types are extended from the `BaseMemory`.

The library has `MemoryFactory` which serves as a single point of contact for Cache to use a certain memory type.
Currently only `InMemory` is implemented but new types can be added with ease.
`MemoryRegistry` maintains the catalogue for the different types of memory available.

FIFO, LFU, LIFO, LRU Cache system are implemented out of the box.

### Adding a new Memory

 - Define your memory type in the `MemoryType` class.
 - Register memory type in the `MemoryRegistry` for guice to find it.
 - While defining Cache, specify the kind of memory you want to use. For eg
```java
        this.memoryImp = memoryInjector.getInstance(MemoryFactory.class).getMemoryImp(MemoryType.fromValue("in_memory"), capacity);
```

### Adding a new Policy
 - Extend your custom policy from the `BasePolicy` class 
and override the specified functions in the interface extended by `BasePolicy`
 - In order to use it, create a Cache which extends your custom policy.

For the convenience, standard eviction policies (FIFO, LFU, LIFO, LRU) are defined and implemented for direct use.
