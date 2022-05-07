package me.adrjan.mcsearch.api.data;

import lombok.Getter;

@Getter
public class Source {

    private final String name;
    private final String contains;
    private final String description;
    private final int size;
    private final float date;

    public Source(long addDate, String[] split) {
        this.name = split[0];
        this.contains = split[1];
        this.description = split[2];
        this.size = Integer.parseInt(split[3]);
        this.date = addDate;
    }

    public Source(String name, String contains, String description, int size, float date) {
        this.name = name;
        this.contains = contains;
        this.description = description;
        this.size = size;
        this.date = date;
    }
}