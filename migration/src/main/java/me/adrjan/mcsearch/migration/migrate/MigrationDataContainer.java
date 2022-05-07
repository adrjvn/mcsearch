package me.adrjan.mcsearch.migration.migrate;

import com.google.common.collect.Lists;
import lombok.Getter;
import me.adrjan.mcsearch.api.data.RecordBase;

import java.util.List;

@Getter
public class MigrationDataContainer {

    private final List<RecordBase> results = Lists.newArrayList();

    public void store(RecordBase migrationResult){
        this.results.add(migrationResult);
    }
}