package me.adrjan.mcsearch.api.data.result;

import lombok.Getter;
import lombok.Setter;
import me.adrjan.mcsearch.api.data.Record;

@Setter
@Getter
public class RecordSearchResult extends SearchResult<Record>{

    private boolean premium = false;
}