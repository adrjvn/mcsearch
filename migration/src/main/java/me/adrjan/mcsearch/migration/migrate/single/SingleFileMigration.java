package me.adrjan.mcsearch.migration.migrate.single;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;
import me.adrjan.mcsearch.api.data.RecordBase;
import me.adrjan.mcsearch.migration.migrate.Migration;
import me.adrjan.mcsearch.migration.migrate.MigrationDataContainer;
import me.adrjan.mcsearch.migration.migrate.geo.GeoResolver;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public abstract class SingleFileMigration extends MigrationDataContainer implements Migration {

    private final GeoResolver geoResolver;

    public abstract void process(List<String> lines, String source);

    @SneakyThrows
    @Override
    public List<RecordBase> migrate(File file, String source) {
        List<String> lines = Files.lines(Path.of(file.getPath() + ".txt"))
                .filter(it -> !it.isEmpty())
                .collect(Collectors.toList());
        process(lines, source);
        return super.getResults();
    }
}