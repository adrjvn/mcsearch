package me.adrjan.mcsearch.search;

import java.util.Set;
import java.util.function.Predicate;

public interface Search<T> {

    Set<T> find(String key);

    Set<T> find(String key, Predicate<T> filter);
}