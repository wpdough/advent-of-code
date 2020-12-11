package com.wpdough.adapterarray;

import com.wpdough.aoc.AocProblem;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AdapterArray extends AocProblem<List<Integer>> {

    @Override
    public List<Integer> parseInput(List<String> input) {
        return input.stream().map(Integer::parseInt).collect(Collectors.toList());
    }

    @Override
    public long partOne(List<Integer> input) {
        input.sort(Comparator.comparingInt(x -> x));

        List<Integer> chain = new ArrayList<>();
        Map<Integer, Integer> differenceTally = new HashMap<>();
        IntStream.range(1, 4).forEach(i -> differenceTally.put(i, 0));
        int previousVoltage = 0;
        for (int i = 0; i < input.size(); i++) {
            int voltage = input.get(i);
            int voltageDifference = voltage - previousVoltage;
            if (voltageDifference <= 3) {
                chain.add(voltage);
                differenceTally.put(voltageDifference, differenceTally.get(voltageDifference) + 1);
                previousVoltage = voltage;
            }
        }
        differenceTally.put(3, differenceTally.get(3) + 1);

        return differenceTally.get(1) * differenceTally.get(3);
    }

    @Override
    public long partTwo(List<Integer> input) {
        return -1;
    }

    public static void main(String[] args) throws IOException {
        new AdapterArray().solve(args[0]);
    }
}
