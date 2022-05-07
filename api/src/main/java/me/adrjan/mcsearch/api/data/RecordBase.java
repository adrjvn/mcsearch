package me.adrjan.mcsearch.api.data;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class RecordBase {

    private final long addDate;
    private final String key;
    private final String source;

    public abstract String migrated();
}