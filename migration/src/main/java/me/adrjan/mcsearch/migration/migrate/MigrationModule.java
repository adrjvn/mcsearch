package me.adrjan.mcsearch.migration.migrate;

import lombok.extern.slf4j.Slf4j;
import me.adrjan.mcsearch.api.Module;
import me.adrjan.mcsearch.api.data.RecordBase;
import me.adrjan.mcsearch.migration.Constans;
import me.adrjan.mcsearch.migration.migrate.geo.GeoResolver;
import me.adrjan.mcsearch.migration.migrate.writer.MigrationWriter;

import java.io.File;
import java.util.List;

@Slf4j
public class MigrationModule implements Module {

    private final GeoResolver geoResolver = new GeoResolver();
    private final MigrationFactory migrationFactory = new MigrationFactory(this.geoResolver);

    public void start() {
        Migration migration = this.migrationFactory.get(Constans.MIGRATION_MODE);
        File file = new File(Constans.PATH_FILE_TO_MIGRATE + Constans.DATA_SOURCE);
        log.info("Loaded file " + file.getPath());
        List<RecordBase> results = migration.migrate(file, Constans.DATA_SOURCE);
        log.info("Migrated " + results.size() + " results. Writing...");
        new MigrationWriter(file).write(results, Constans.DATA_SOURCE);
        log.info("Done");
    }
}