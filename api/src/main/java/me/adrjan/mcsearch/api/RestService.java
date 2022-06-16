package me.adrjan.mcsearch.api;

public interface RestService<T> {

    T get(String url);
}