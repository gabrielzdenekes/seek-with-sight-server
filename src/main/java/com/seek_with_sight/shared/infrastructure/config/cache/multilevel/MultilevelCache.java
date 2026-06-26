package com.seek_with_sight.shared.infrastructure.config.cache.multilevel;

import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.cache.Cache;

import java.util.concurrent.Callable;

@RequiredArgsConstructor
public class MultilevelCache implements Cache {
    private final Cache l1Cache;
    private final Cache l2Cache;

    @Override
    public String getName() {
        return l1Cache.getName();
    }

    @Override
    public Object getNativeCache() {
        return this;
    }

    @Override
    public @Nullable ValueWrapper get(Object key) {
        var value = l1Cache.get(key);

        if (value != null) {
            return value;
        }

        value = l2Cache.get(key);

        if (value != null) {
            l1Cache.put(key, value.get());
        }

        return value;
    }

    @Override
    public @Nullable <T> T get(Object key, @Nullable Class<T> type) {
        T value = l1Cache.get(key, type);

        if (value != null) {
            return value;
        }

        value = l2Cache.get(key, type);

        if (value != null) {
            l1Cache.put(key, value);
        }

        return value;
    }

    @Override
    public @Nullable <T> T get(Object key, Callable<T> valueLoader) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void put(Object key, @Nullable Object value) {

    }

    @Override
    public @Nullable ValueWrapper putIfAbsent(Object key, @Nullable Object value) {
        var existing = get(key);

        if (existing == null) {
            put(key, value);
        }

        return existing;
    }

    @Override
    public void evict(Object key) {
        l1Cache.evict(key);
        l2Cache.evict(key);
    }

    @Override
    public boolean evictIfPresent(Object key) {
        return l1Cache.evictIfPresent(key) ||
                l2Cache.evictIfPresent(key);
    }

    @Override
    public void clear() {
        l1Cache.clear();
        l2Cache.clear();
    }

    @Override
    public boolean invalidate() {
        return l1Cache.invalidate() ||
                l2Cache.invalidate();
    }
}
