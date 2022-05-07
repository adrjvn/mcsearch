package me.adrjan.mcsearch.api.cache;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.Optional;

public class LocalCache<K, V> implements Cache<V> {

    private final Map<K, V> map = Maps.newConcurrentMap();

    public void store(K key, V value) {
        this.map.put(key, value);
    }

    public Optional<V> get(K key) {
        return Optional.ofNullable(this.map.get(key));
    }
}