package com.wpdough.ramrecit;

import com.wpdough.aoc.AocProblem;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class RambunctiousRecitation extends AocProblem<List<Integer>> {
    @Override
    public List<Integer> parseInput(List<String> input) {
        return Arrays.stream(input.stream()
                .findFirst()
                .get()
                .split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    @Override
    public long partOne(List<Integer> input) {
        return findNthSpokenNumber(input, 2020);
    }

    @Override
    public long partTwo(List<Integer> input) {
        return findNthSpokenNumber(input, 30000000);
    }

    public static int findNthSpokenNumber(List<Integer> startingNumbers, int n) {
        Map<Integer, List<Integer>> numberToTurnsSpoken = new HashMap<>();
        for (int i = 0; i < startingNumbers.size(); i++) {
            numberToTurnsSpoken.put(startingNumbers.get(i), new ArrayList<>(Arrays.asList(i + 1)));
        }

        int lastNumber = startingNumbers.get(startingNumbers.size() - 1);
        for (int i = startingNumbers.size(); i < n; i++) {
            int turnNumber = i + 1;

            List<Integer> lastNumberTurnsSpoken = numberToTurnsSpoken.getOrDefault(lastNumber, new ArrayList<>());
            if (lastNumberTurnsSpoken.size() == 1) {
                lastNumber = 0;
            } else {
                lastNumber = lastNumberTurnsSpoken.get(lastNumberTurnsSpoken.size() - 1) -
                        lastNumberTurnsSpoken.get(lastNumberTurnsSpoken.size() - 2);
            }

            lastNumberTurnsSpoken = numberToTurnsSpoken.getOrDefault(lastNumber, new ArrayList<>());
            lastNumberTurnsSpoken.add(turnNumber);
            numberToTurnsSpoken.put(lastNumber, lastNumberTurnsSpoken);
        }

        return lastNumber;
    }

    public static void main(String[] args) throws IOException {
        new RambunctiousRecitation().solve(args[0]);
    }
}
