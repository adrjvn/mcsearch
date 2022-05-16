package me.adrjan.mcsearch.api.cache;

import com.google.common.collect.Maps;

import java.util.*;

public class LocalCache<K, V> implements Cache<V> {

    private final Map<K, V> map = Maps.newConcurrentMap();

    public void store(K key, V value) {
        this.map.put(key, value);
    }

    public void remove(K key) {
        this.map.remove(key);
    }

    public Optional<V> get(K key) {
        return Optional.ofNullable(this.map.get(key));
    }

    public Set<V> values() {
        return Collections.unmodifiableSet(new HashSet<>(this.map.values()));
    }
}