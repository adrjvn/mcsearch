package me.adrjan.mcsearch.api.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class LocalTempCache<K, V> implements me.adrjan.mcsearch.api.cache.Cache<V> {

    private final Cache<K, V> map = CacheBuilder.newBuilder().expireAfterAccess(30, TimeUnit.MINUTES).build();

    public void store(K key, V value) {
        if (map.getIfPresent(key) != null) return;
        this.map.put(key, value);
    }

    public Optional<V> get(K key) {
        return Optional.ofNullable(this.map.getIfPresent(key));
    }

    public Set<V> values() {
        return Set.copyOf(this.map.asMap().values());
    }
}