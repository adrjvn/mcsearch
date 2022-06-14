package me.adrjan.mcsearch.search;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import me.adrjan.mcsearch.api.cache.LocalTempCache;
import me.adrjan.mcsearch.api.data.result.SearchResult;
import me.adrjan.mcsearch.api.redis.RedisDataSource;

import java.util.Locale;
import java.util.Optional;

@Slf4j
@Getter
@AllArgsConstructor
public abstract class SearchWrapper<T> implements Search<T> {

    private final RedisDataSource redisDataSource;
    private final LocalTempCache<String, SearchResult<T>> localTempCache = new LocalTempCache<>();

    public abstract SearchResult<T> search(String key);

    @Override
    public SearchResult<T> find(String key) {
        key = key.toLowerCase(Locale.ROOT);
        Optional<SearchResult<T>> cached = this.localTempCache.get(key);
        if (cached.isPresent()) return cached.get();

        SearchResult<T> result = search(key);
        this.localTempCache.store(key, result);
        return result;
    }
}