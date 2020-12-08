package com.wpdough.handhalt;

import lombok.Value;

import java.util.ArrayList;
import java.util.List;

public class InstructionRunner {

    public static Result execute(List<Instruction> instructions) {
        boolean success = true;
        int accumulator = 0;
        List<Integer> instructionIndicesExecuted = new ArrayList<>();
        for (int i = 0; i < instructions.size(); i++) {
            Instruction instruction = instructions.get(i);
            if (!instructionIndicesExecuted.contains(i)) {
                instructionIndicesExecuted.add(i);
                switch (instruction.getOperation()) {
                    case NOP: break;
                    case JMP: i += instruction.getArgument() - 1; break;
                    case ACC: accumulator += instruction.getArgument(); break;
                }
            } else {
                success = false;
            }
        }
        return new Result(accumulator, success);
    }

    @Value
    public static class Result {
        private int accumulator;
        private boolean success;
    }
}
