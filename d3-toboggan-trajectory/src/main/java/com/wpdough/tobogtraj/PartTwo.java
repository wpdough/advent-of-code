package com.wpdough.tobogtraj;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.function.IntBinaryOperator;
import java.util.stream.Collectors;

public class PartTwo {
    public static final char TREE_TILE = '#';

    public static void main(String[] args) throws IOException {
        String fileName = args[0];
        List<String> lines = Files.lines(Paths.get(fileName))
                .collect(Collectors.toList());

        List<Slope> slopes = Arrays.asList(
                new Slope(1, 1),
                new Slope(3, 1),
                new Slope(5, 1),
                new Slope(7, 1),
                new Slope(1, 2));

        long product = slopes.stream()
                .mapToLong(slope -> calculateNumTrees(lines, slope))
                .reduce((left, right) -> left * right)
                .getAsLong();

        System.out.println(product);
    }

    private static int calculateNumTrees(List<String> tiles, Slope slope) {
        int maxY = tiles.size();

        int x = 0, y = 0, numTrees = 0;
        while (y < maxY) {
            if (hasTree(tiles, x, y)) numTrees++;
            x += slope.getX();
            y += slope.getY();
        }

        return numTrees;
    }

    private static boolean hasTree(List<String> tiles, int x, int y) {
        String row = tiles.get(y);
        char tile = row.charAt(x % row.length());
        return tile == TREE_TILE;
    }
}
