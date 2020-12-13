package com.wpdough.aoc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AocProblem<T> {

    public abstract T parseInput(List<String> input);

    public abstract long partOne(T input);

    public abstract long partTwo(T input);

    public void solve(String fileName) throws IOException {
        List<String> fileLines = Files.lines(Paths.get(fileName))
                .collect(Collectors.toList());
        T input = parseInput(fileLines);
        System.out.println(partOne(input));
        input = parseInput(fileLines);
        System.out.println(partTwo(input));
    }
}
