package com.seek_with_sight.shared.infrastructure.adapter.out.persistence;

import java.util.IdentityHashMap;

public class CycleAvoidingMappingContext {
    private final IdentityHashMap<Object, Object> knownInstances = new IdentityHashMap<>();

    @SuppressWarnings("unchecked")
    public <T> T getMappedInstance(Object source, Class<T> targetType) {
        return (T) knownInstances.get(source);
    }

    public void storeMappedInstance(Object source, Object target) {
        knownInstances.put(source, target);
    }
}
