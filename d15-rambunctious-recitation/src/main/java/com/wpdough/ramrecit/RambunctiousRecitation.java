package com.wpdough.ramrecit;

import com.wpdough.aoc.AocProblem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
        return 0;
    }

    public static int findNthSpokenNumber(List<Integer> startingNumbers, int n) {
        List<Integer> spokenNumbers = new ArrayList<>(startingNumbers);
        for (int i = spokenNumbers.size(); i <= n; i++) {
            int lastNumber = spokenNumbers.get(spokenNumbers.size() - 1);
            long lastNumberFreq = spokenNumbers.stream().filter(num -> num == lastNumber).count();
            if (lastNumberFreq == 1) {
                spokenNumbers.add(0);
            } else {
                int turnLastSpoken = findLastTurnSpoken(spokenNumbers, lastNumber);
                int beforeTurnLastSpoken = findLastTurnSpoken(spokenNumbers.subList(0, turnLastSpoken - 1), lastNumber);
                spokenNumbers.add(turnLastSpoken - beforeTurnLastSpoken);
            }
        }
        return spokenNumbers.get(n - 1);
    }

    private static int findLastTurnSpoken(List<Integer> spokenNumbers, int number) {
        // index of last number + 1
        for (int i = spokenNumbers.size() - 1; i >= 0; i--) {
            if (spokenNumbers.get(i) == number)
                return i + 1;
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        new RambunctiousRecitation().solve(args[0]);
    }
}
