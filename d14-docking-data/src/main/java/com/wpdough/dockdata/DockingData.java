package com.wpdough.dockdata;

import com.wpdough.aoc.AocProblem;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DockingData extends AocProblem<List<Instruction>> {

    @Override
    public List<Instruction> parseInput(List<String> input) {
        return input.stream()
                .map(Instruction::parse)
                .collect(Collectors.toList());
    }

    @Override
    public long partOne(List<Instruction> input) {

        Map<Integer, Long> memory = new HashMap<>();

        String bitmask = null;
        for (Instruction instruction : input) {
            switch (instruction.getType()) {
                case UPDATE_BITMASK:
                    bitmask = instruction.getBitmask();
                    break;
                case WRITE_VALUE:
                    memory.put(instruction.getAddress(), applyBitmask(instruction.getValue(), bitmask));
                    break;
            }
        }

        return memory.values().stream().mapToLong(x -> x).sum();
    }

    @Override
    public long partTwo(List<Instruction> input) {
        return 0;
    }

    private static long applyBitmask(Long value, String bitmask) {
        String binaryStr = Long.toBinaryString(value);

        while (binaryStr.length() < 36) {
            binaryStr = "0" + binaryStr;
        }

        char[] binary = binaryStr.toCharArray();

        char[] bitmaskCharArr = bitmask.toCharArray();
        for (int i = 0; i < bitmaskCharArr.length; i++) {
            char c = bitmaskCharArr[i];
            if (c == '0' || c == '1') {
                binary[i] = c;
            }
        }

        return Long.parseLong(new String(binary), 2);
    }

    public static void main(String[] args) throws IOException {
        new DockingData().solve(args[0]);
    }
}
