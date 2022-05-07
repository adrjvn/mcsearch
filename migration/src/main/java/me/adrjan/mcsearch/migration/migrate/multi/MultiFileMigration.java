package me.adrjan.mcsearch.migration.migrate.multi;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import me.adrjan.mcsearch.api.data.RecordBase;
import me.adrjan.mcsearch.migration.migrate.Migration;
import me.adrjan.mcsearch.migration.migrate.MigrationDataContainer;
import me.adrjan.mcsearch.migration.migrate.geo.GeoResolver;

import java.io.File;
import java.util.List;

@Setter
@Getter
@Slf4j
@AllArgsConstructor
public abstract class MultiFileMigration extends MigrationDataContainer implements Migration {

    private final GeoResolver geoResolver;
    private final String fileSufix;

    public abstract void process(File file, String source);

    @Override
    public List<RecordBase> migrate(File file, String source) {
        File[] files = file.listFiles((dir, name) -> name.endsWith(fileSufix));
        log.info("Loaded " + files.length + " files.");
        for (File f : files) process(f, source);
        return super.getResults();
    }
}