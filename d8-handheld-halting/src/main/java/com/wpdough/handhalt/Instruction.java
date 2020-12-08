package com.wpdough.handhalt;

import lombok.Value;

import java.util.Arrays;

@Value
public class Instruction {
    private Operation operation;
    private int argument;

    public static Instruction parse(String line) {
        Operation operation = Arrays.stream(Operation.values())
                .filter(op -> line.contains(op.toString().toLowerCase()))
                .findFirst()
                .get();
        int argument = Integer.parseInt(line.replace(operation.toString().toLowerCase() + " ", ""));
        return new Instruction(operation, argument);
    }
}
