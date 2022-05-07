package me.adrjan.mcsearch.api.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class LocalTempCache<K, V> implements me.adrjan.mcsearch.api.cache.Cache<V> {

    private final Cache<K, V> map = CacheBuilder.newBuilder().expireAfterWrite(30, TimeUnit.MINUTES).build();

    public void store(K key, V value) {
        if (map.getIfPresent(key) != null) return;
        this.map.put(key, value);
    }

    public Optional<V> get(K key) {
        V value = this.map.getIfPresent(key);
        if (value == null) return Optional.empty();
        return Optional.of(value);
    }
}