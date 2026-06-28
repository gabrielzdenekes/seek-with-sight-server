package com.seek_with_sight.product.infrastructure.adapter.out.persistence.mapper;

import java.util.IdentityHashMap;

public class CycleAvoidingMappingContext {
    private final IdentityHashMap<Object, Object> knownInstances = new IdentityHashMap<>();
}
