package me.adrjan.mcsearch.search.filter;

import java.util.Set;

public interface Filter<T> {

    Set<T> filter(Set<T> toFilter);
}