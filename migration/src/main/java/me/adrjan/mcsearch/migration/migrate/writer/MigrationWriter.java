package me.adrjan.mcsearch.migration.migrate.writer;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import me.adrjan.mcsearch.api.data.RecordBase;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@AllArgsConstructor
public class MigrationWriter {

    private final File file;

    @SneakyThrows
    public void write(List<RecordBase> migrationResults, String source) {
        List<String> done = new ArrayList<>();
        String outPath = file.getPath() + "out-" + source + ".txt";
        PrintWriter printWriter = new PrintWriter(new FileWriter(outPath));

        AtomicInteger atomicInteger = new AtomicInteger();
        migrationResults.forEach(result -> {
            String string = result.migrated();
            if (done.contains(string)) return;
            done.add(string);
            printWriter.write(string + "\n");
            atomicInteger.incrementAndGet();
        });

        printWriter.close();
        log.info("Writen " + atomicInteger.get() + " results from " + source);
    }
}