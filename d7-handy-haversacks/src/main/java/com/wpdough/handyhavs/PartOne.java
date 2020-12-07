package com.wpdough.handyhavs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class PartOne {

    public static void main(String[] args) throws IOException {
        String fileName = args[0];
        List<String> lines = Files.lines(Paths.get(fileName))
                .collect(Collectors.toList());

        long acceptableBags = lines.stream()
                .map(Bag::fromString)
                .filter(bag -> bag.canContain("shiny gold"))
                .count();

        System.out.println(acceptableBags);
    }
}
