package me.adrjan.mcsearch.migration.migrate.single;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;
import me.adrjan.mcsearch.api.data.RecordBase;
import me.adrjan.mcsearch.migration.migrate.Migration;
import me.adrjan.mcsearch.migration.migrate.MigrationDataContainer;
import me.adrjan.mcsearch.migration.migrate.geo.GeoResolver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

@Getter
@AllArgsConstructor
public abstract class SingleFileMigration extends MigrationDataContainer implements Migration {

    private final GeoResolver geoResolver;

    public abstract void process(BufferedReader bufferedReader, String source);

    @SneakyThrows
    @Override
    public List<RecordBase> migrate(File file, String source) {
        BufferedReader reader = new BufferedReader(new FileReader(file.getPath() + ".txt"));
        process(reader, source);
        return super.getResults();
    }
}