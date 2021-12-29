package com.adrij.cache.memory;

import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter

public enum MemoryType {
    IN_MEMORY("in_memory");
    private final String value;

    MemoryType(String value) {
        this.value = value;
    }

    public static MemoryType fromValue(String value) {
        Optional<MemoryType> memoryType = Arrays.stream(MemoryType.values())
                .filter(type -> type.value.equals(value)).findFirst();

        if (!memoryType.isPresent()) {
            throw new IllegalArgumentException(String.format("Illegal Memory Type: %s", value));
        }
        return memoryType.get();
    }
}
