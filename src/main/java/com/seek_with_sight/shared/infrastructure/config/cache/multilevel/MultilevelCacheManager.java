package com.seek_with_sight.shared.infrastructure.config.cache.multilevel;

import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
public class MultilevelCacheManager implements CacheManager {
    private final CacheManager l1CacheManager;
    private final CacheManager l2CacheManager;

    @Override
    public @Nullable Cache getCache(String name) {
        var l1 = l1CacheManager.getCache(name);
        var l2 = l2CacheManager.getCache(name);

        if (l1 == null || l2 == null) {
            return null;
        }

        return new MultilevelCache(l1, l2);
    }

    @Override
    public Collection<String> getCacheNames() {
        Set<String> names = new HashSet<>();

        names.addAll(l1CacheManager.getCacheNames());
        names.addAll(l2CacheManager.getCacheNames());

        return names;
    }
}
