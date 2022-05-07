package me.adrjan.mcsearch.migration.injection;

import lombok.extern.slf4j.Slf4j;
import me.adrjan.mcsearch.api.Module;
import me.adrjan.mcsearch.api.redis.RedisDataSource;
import me.adrjan.mcsearch.migration.Constans;

import java.io.File;

@Slf4j
public class InjectorModule implements Module {

    private final RedisDataSource redisDataSource = new RedisDataSource("redis://127.0.0.1:6379", "");
    private final InjectorFactory injectorFactory = new InjectorFactory(this.redisDataSource);

    public void start() {
        Injector injector = this.injectorFactory.get(Constans.INJECTION_MODE);
        File file = new File(Constans.PATH_FILE_TO_INJECT + Constans.DATA_SOURCE);
        log.info("Loaded file " + file.getPath());
        injector.inject(file, Constans.DATA_SOURCE);
        log.info("Done");
    }
}