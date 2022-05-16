package me.adrjan.mcsearch.migration.injection.mode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import me.adrjan.mcsearch.api.data.Source;
import me.adrjan.mcsearch.api.redis.RedisDataSource;
import me.adrjan.mcsearch.api.redis.SearchMap;
import me.adrjan.mcsearch.migration.Constans;
import me.adrjan.mcsearch.migration.injection.Injector;
import me.adrjan.mcsearch.search.Search;
import me.adrjan.mcsearch.search.impl.SourceSearch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;

@Slf4j
@AllArgsConstructor
@Getter
public abstract class SingleFileInjector<T> implements Injector {

    private final RedisDataSource redisDataSource;
    private final String searchMap;
    private final BiFunction<Long, String[], T> buildFunction;

    @SneakyThrows
    @Override
    public void inject(File file, String source) {
        final long date = file.lastModified();
        BufferedReader reader = new BufferedReader(new FileReader(file.getPath() + ".txt"));
        String line = reader.readLine();
        AtomicInteger atomicInteger = new AtomicInteger();
        while (line != null) {
            if (line.isEmpty()) line = reader.readLine();
            String[] split = line.split("\\|");


            T object = this.buildFunction.apply(date, split);
            String serialized = this.redisDataSource.getSerializer().serialize(object);
            log.info(serialized);

            //this.redisDataSource.injectToList(
            //        getSearchMap(),
            //        split[0].toLowerCase(Locale.ROOT), // 0 should always be key
            //        this.buildFunction.apply(date, split));

            atomicInteger.incrementAndGet();
            line = reader.readLine();
        }
        if (Constans.INJECT_RECORDS_AND_SOURCE && !searchMap.equals(SearchMap.MAP_SOURCES)) {
            Search<Source> search = new SourceSearch(this.redisDataSource);
            Set<Source> set = search.find(source);
            if (set.isEmpty()) {
                this.redisDataSource.inject(
                        SearchMap.MAP_SOURCES,
                        source,
                        new Source(
                                source,
                                "-",
                                "-",
                                atomicInteger.get(),
                                date));
                log.info("Injected source=" + source);
            }
        }
        log.info("Injected " + atomicInteger.get() + " records.");
    }
}