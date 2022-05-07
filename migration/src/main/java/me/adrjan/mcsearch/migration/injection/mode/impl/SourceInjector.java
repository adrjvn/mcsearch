package me.adrjan.mcsearch.migration.injection.mode.impl;

import me.adrjan.mcsearch.api.data.Source;
import me.adrjan.mcsearch.api.redis.RedisDataSource;
import me.adrjan.mcsearch.api.redis.SearchMap;
import me.adrjan.mcsearch.migration.injection.mode.SingleFileInjector;

public class SourceInjector extends SingleFileInjector<Source> {

    public SourceInjector(RedisDataSource redisDataSource) {
        super(redisDataSource, SearchMap.MAP_SOURCES, Source::new);
    }
}