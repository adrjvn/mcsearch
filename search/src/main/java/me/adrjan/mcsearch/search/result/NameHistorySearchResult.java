package me.adrjan.mcsearch.search.result;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NameHistorySearchResult extends SearchResult<String> {

    private boolean premium = false;
}