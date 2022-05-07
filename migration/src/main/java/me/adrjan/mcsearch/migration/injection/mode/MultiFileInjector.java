package me.adrjan.mcsearch.migration.injection.mode;

import lombok.AllArgsConstructor;
import me.adrjan.mcsearch.migration.injection.Injector;

import java.io.File;
import java.util.Arrays;

@AllArgsConstructor
public class MultiFileInjector implements Injector {

    private final SingleFileInjector<?> singleFileInjector;

    @Override
    public void inject(File file, String source) {
        File[] files = file.listFiles((dir1, name) -> name.endsWith(".txt"));
        Arrays.stream(files).forEach(it -> this.singleFileInjector.inject(it, source));
    }
}