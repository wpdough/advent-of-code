package com.wpdough.encodingerror;

import com.wpdough.aoc.AocProblem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EncodingError extends AocProblem<List<Long>> {
    private int preambleSize;

    public EncodingError(int preambleSize) {
        this.preambleSize = preambleSize;
    }

    @Override
    public List<Long> parseInput(List<String> input) {
        return input.stream().map(Long::parseLong).collect(Collectors.toList());
    }

    @Override
    public long partOne(List<Long> input) {
        for (int i = preambleSize; i < input.size(); i++) {
            List<Long> preamble = input.subList(i - preambleSize, i);
            Long target = input.get(i);

            if (!listContainsPairWithSum(preamble, target)) {
                return target;
            }
        }

        return -1;
    }

    @Override
    public long partTwo(List<Long> input) {
        Long target = partOne(input);

        for (int i = 0; i < input.size(); i++) {
            int sumIdx = 0;
            long sum = 0;
            List<Long> summed = new ArrayList<>();
            while (sum < target) {
                sum += input.get(i + sumIdx);
                summed.add(input.get(i + sumIdx));
                sumIdx++;
            }
            if (sum == target) {
                return summed.stream().mapToLong(x -> x).min().getAsLong() +
                        summed.stream().mapToLong(x -> x).max().getAsLong();
            }
        }

        return -1;
    }

    private boolean listContainsPairWithSum(List<Long> values, long desiredSum) {
        for (int i = 0; i < values.size(); i++) {
            for (int j = 0; j < values.size(); j++) {
                Long a = values.get(i);
                Long b = values.get(j);
                if (i == j)
                    continue;
                if ((a + b) == desiredSum) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        String fileName = args[0];
        int preambleSize = Integer.parseInt(args[1]);
        new EncodingError(preambleSize).solve(fileName);
    }
}
