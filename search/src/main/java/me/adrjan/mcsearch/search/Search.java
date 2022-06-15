package me.adrjan.mcsearch.search;

import me.adrjan.mcsearch.search.result.SearchResult;

public interface Search<T> {

    SearchResult<T> find(String key);
}