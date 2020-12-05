package com.wpdough.tobogtraj;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class PartOne {
    public static final char TREE_TILE = '#';
    public static final int SLOPE_X = 3;
    public static final int SLOPE_Y = 1;

    public static void main(String[] args) throws IOException {
        String fileName = args[0];
        List<String> lines = Files.lines(Paths.get(fileName))
                .collect(Collectors.toList());

        int maxY = lines.size();

        int x = 0, y = 0, numTrees = 0;
        while (y < maxY) {
            if (hasTree(lines, x, y)) numTrees++;
            x += SLOPE_X;
            y += SLOPE_Y;
        }

        System.out.println(numTrees);
    }

    private static boolean hasTree(List<String> tiles, int x, int y) {
        String row = tiles.get(y);
        char tile = row.charAt(x % row.length());
        return tile == TREE_TILE;
    }
}
