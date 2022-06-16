package me.adrjan.mcsearch.search.grabber;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class GrabberWrapper implements Grabber {

    public abstract Set<String> solve(List<String> input);

    @Override
    public Set<String> grab(String input) {
        return solve(
                Arrays.stream(input
                        .split("\n")).map(it -> it.replace(" ", ""))
                        .collect(Collectors.toList()));
    }
}
