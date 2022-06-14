package me.adrjan.mcsearch.search;

import me.adrjan.mcsearch.api.data.result.SearchResult;

public interface Search<T> {

    SearchResult<T> find(String key);
}