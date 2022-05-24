package me.adrjan.mcsearch.search;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.adrjan.mcsearch.api.cache.LocalTempCache;
import me.adrjan.mcsearch.api.redis.RedisDataSource;

import java.util.Locale;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public abstract class SearchWrapper<T> implements Search<T> {

    private final RedisDataSource redisDataSource;
    private final LocalTempCache<String, Set<T>> localTempCache = new LocalTempCache<>();

    public abstract Set<T> search(String key);

    @Override
    public Set<T> find(String key) {
        key = key.toLowerCase(Locale.ROOT);

        Optional<Set<T>> cached = this.localTempCache.get(key);
        if (cached.isPresent()) return cached.get();

        Set<T> result = search(key);
        this.localTempCache.store(key, result);
        return result;
    }

    public Set<T> find(String key, Predicate<T> filter) {
        return this.find(key).stream().filter(filter).collect(Collectors.toSet());
    }
}