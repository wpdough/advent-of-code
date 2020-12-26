package com.wpdough.dockdata;

import com.wpdough.aoc.AocProblem;

import java.io.IOException;
import java.util.*;
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

        Map<Long, Long> memory = new HashMap<>();

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

        Map<Long, Long> memory = new HashMap<>();

        String bitmask = null;
        for (Instruction instruction : input) {
            switch (instruction.getType()) {
                case UPDATE_BITMASK:
                    bitmask = instruction.getBitmask();
                    break;
                case WRITE_VALUE:
                    List<Long> addresses = findAddresses(bitmask, instruction.getAddress());
                    addresses.forEach(addr -> memory.put(addr, instruction.getValue()));
                    break;
            }
        }

        return memory.values().stream().mapToLong(x -> x).sum();
    }

    private static long applyBitmask(Long value, String bitmask) {
        String binaryStr = toBinaryStringBase36(value);

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

    private static List<Long> findAddresses(String bitmask, long address) {
        String binaryStr = toBinaryStringBase36(address);

        char[] binary = binaryStr.toCharArray();
        char[] bitmaskCharArr = bitmask.toCharArray();
        for (int i = 0; i < bitmaskCharArr.length; i++) {
            char c = bitmaskCharArr[i];
            if (c == '1' || c == 'X') {
                binary[i] = c;
            }
        }

        String maskedAddress = new String(binary);

        return getAddressPossibilities(Arrays.asList(maskedAddress));
    }

    private static List<Long> getAddressPossibilities(List<String> addresses) {
        List<String> needToProcess = addresses.stream()
                .filter(DockingData::addressContainsX)
                .collect(Collectors.toList());
        if (needToProcess.isEmpty()) {
            return addresses.stream()
                    .map(addr -> Long.parseLong(addr, 2))
                    .collect(Collectors.toList());
        }

        List<String> newAddresses = addresses.stream()
                .filter(x -> !addressContainsX(x))
                .collect(Collectors.toList());

        needToProcess.forEach(addr -> {
            String replaceWithZero = addr.replaceFirst("X", "0");
            String replaceWithOne = addr.replaceFirst("X", "1");
            newAddresses.add(replaceWithZero);
            newAddresses.add(replaceWithOne);
        });

        return getAddressPossibilities(newAddresses);
    }

    private static boolean addressContainsX(String address) {
        return address.contains("X");
    }

    private static String toBinaryStringBase36(long value) {
        String binaryStr = Long.toBinaryString(value);

        while (binaryStr.length() < 36) {
            binaryStr = "0" + binaryStr;
        }

        return binaryStr;
    }

    public static void main(String[] args) throws IOException {
        new DockingData().solve(args[0]);
    }
}
