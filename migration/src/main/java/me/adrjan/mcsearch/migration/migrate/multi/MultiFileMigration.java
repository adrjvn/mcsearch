package me.adrjan.mcsearch.migration.migrate.multi;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import me.adrjan.mcsearch.api.data.RecordBase;
import me.adrjan.mcsearch.migration.migrate.Migration;
import me.adrjan.mcsearch.migration.migrate.MigrationDataContainer;
import me.adrjan.mcsearch.migration.migrate.geo.GeoResolver;

import java.io.File;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@Slf4j
@AllArgsConstructor
public abstract class MultiFileMigration extends MigrationDataContainer implements Migration {

    private final GeoResolver geoResolver;
    private final String fileSufix;

    public abstract void process(List<String> lines, String fileName, String source);

    @SneakyThrows
    @Override
    public List<RecordBase> migrate(File file, String source) {
        File[] files = file.listFiles((dir, name) -> name.endsWith(fileSufix));
        log.info("Loaded " + files.length + " files.");
        for (File f : files) {
            List<String> lines = Files.lines(f.toPath())
                    .filter(it -> !it.isEmpty())
                    .collect(Collectors.toList());
            process(lines, file.getName().split("\\.")[0], source);
        }
        return super.getResults();
    }
}