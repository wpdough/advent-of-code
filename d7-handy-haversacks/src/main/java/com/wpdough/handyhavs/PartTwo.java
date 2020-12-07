package com.wpdough.handyhavs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class PartTwo {

    public static void main(String[] args) throws IOException {
        String fileName = args[0];
        List<String> lines = Files.lines(Paths.get(fileName))
                .collect(Collectors.toList());

        int requiredBags = lines.stream()
                .map(Bag::fromString)
                .filter(bag -> bag.getColor().equals("shiny gold"))
                .findFirst()
                .get()
                .bagChildrenCount();

        System.out.println(requiredBags);
    }
}
