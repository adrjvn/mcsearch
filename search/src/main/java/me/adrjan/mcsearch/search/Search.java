package me.adrjan.mcsearch.search;

import me.adrjan.mcsearch.search.filter.Filter;

import java.util.Set;

public interface Search<T> {

    Set<T> find(String key);

    Set<T> find(String key, Filter<T> filter);
}