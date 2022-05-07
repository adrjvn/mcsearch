package me.adrjan.mcsearch.migration.injection;

import java.io.File;

public interface Injector {

    void inject(File file, String source);
}