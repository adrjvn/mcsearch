package me.adrjan.mcsearch.api.data.result;

import com.google.common.collect.Sets;
import lombok.Getter;

import java.util.Set;


@Getter
public abstract class SearchResult<T> {

    private Set<T> results = Sets.newHashSet();

    public SearchResult<T> setResults(Set<T> set) {
        this.results = set;
        return this;
    }

    public SearchResult<T> addResult(T result) {
        this.results.add(result);
        return this;
    }
}