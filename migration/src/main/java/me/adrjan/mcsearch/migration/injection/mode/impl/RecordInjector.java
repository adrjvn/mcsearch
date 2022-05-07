package me.adrjan.mcsearch.migration.injection.mode.impl;

import me.adrjan.mcsearch.api.data.Record;
import me.adrjan.mcsearch.api.redis.RedisDataSource;
import me.adrjan.mcsearch.api.redis.SearchMap;
import me.adrjan.mcsearch.migration.injection.mode.SingleFileInjector;

public class RecordInjector extends SingleFileInjector<Record> {

    public RecordInjector(RedisDataSource redisDataSource) {
        super(redisDataSource, SearchMap.MAP_RECORDS, Record::new);
    }
}