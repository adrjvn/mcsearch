package me.adrjan.mcsearch.migration.migrate;

import me.adrjan.mcsearch.api.data.RecordBase;

import java.io.File;
import java.util.List;

public interface Migration {
    List<RecordBase> migrate(File file, String source);
}