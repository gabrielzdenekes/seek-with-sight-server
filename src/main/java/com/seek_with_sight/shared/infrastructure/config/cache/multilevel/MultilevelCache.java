package com.seek_with_sight.shared.infrastructure.config.cache.multilevel;

import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.cache.Cache;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

@RequiredArgsConstructor
public class MultilevelCache implements Cache {
    private final Cache l1Cache;
    private final Cache l2Cache;

    @Override
    public String getName() {
        return "";
    }

    @Override
    public Object getNativeCache() {
        return this;
    }

    @Override
    public @Nullable ValueWrapper get(Object key) {
        return null;
    }

    @Override
    public @Nullable <T> T get(Object key, @Nullable Class<T> type) {
        return null;
    }

    @Override
    public @Nullable <T> T get(Object key, Callable<T> valueLoader) {
        return null;
    }

    @Override
    public @Nullable CompletableFuture<?> retrieve(Object key) {
        return Cache.super.retrieve(key);
    }

    @Override
    public <T> CompletableFuture<T> retrieve(Object key, Supplier<CompletableFuture<T>> valueLoader) {
        return Cache.super.retrieve(key, valueLoader);
    }

    @Override
    public void put(Object key, @Nullable Object value) {

    }

    @Override
    public @Nullable ValueWrapper putIfAbsent(Object key, @Nullable Object value) {
        return Cache.super.putIfAbsent(key, value);
    }

    @Override
    public void evict(Object key) {

    }

    @Override
    public boolean evictIfPresent(Object key) {
        return Cache.super.evictIfPresent(key);
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean invalidate() {
        return Cache.super.invalidate();
    }
}
