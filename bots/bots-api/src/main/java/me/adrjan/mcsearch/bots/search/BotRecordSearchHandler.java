package me.adrjan.mcsearch.bots.search;

import me.adrjan.mcsearch.api.data.Record;
import me.adrjan.mcsearch.api.redis.RedisDataSource;
import me.adrjan.mcsearch.bots.data.account.Account;
import me.adrjan.mcsearch.bots.data.logs.Log;
import me.adrjan.mcsearch.bots.data.logs.LogType;
import me.adrjan.mcsearch.bots.service.ExecutorService;
import me.adrjan.mcsearch.bots.storage.mongo.MongoDataSource;

import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BotRecordSearchHandler {

    private final MongoDataSource mongoDataSource;
    private final ExecutorService executorService;
    private final BotRecordSearch botRecordSearch;

    public BotRecordSearchHandler(RedisDataSource redisDataSource, MongoDataSource mongoDataSource, ExecutorService executorService) {
        this.mongoDataSource = mongoDataSource;
        this.executorService = executorService;
        this.botRecordSearch = new BotRecordSearch(redisDataSource);
    }

    public Set<Record> search(String key, Account account) {
        Log log = new Log(account.getId(), LogType.SEARCH, key, System.currentTimeMillis());
        this.executorService.async(() -> log.save(this.mongoDataSource));
        return this.botRecordSearch.search(key);
    }

    public Set<Record> search(String key, Account account, Predicate<Record> predicate) {
        return this.search(key, account).stream().filter(predicate).collect(Collectors.toSet());
    }
}