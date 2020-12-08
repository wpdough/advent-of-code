package com.wpdough.handhalt;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PartTwo {

    public static void main(String[] args) throws IOException {
        String fileName = args[0];
        List<Instruction> instructions = Files.lines(Paths.get(fileName))
                .map(Instruction::parse)
                .collect(Collectors.toList());

        for (int i = 0; i < instructions.size(); i++) {
            Instruction instruction = instructions.get(i);
            Operation operation = instruction.getOperation();
            if (operation.equals(Operation.NOP) || operation.equals(Operation.JMP)) {
                Operation newOp = operation.equals(Operation.NOP) ? Operation.JMP : Operation.NOP;
                List<Instruction> newInstructions = generateCopyWithReplacement(instructions, i, newOp);
                InstructionRunner.Result result = InstructionRunner.execute(newInstructions);
                if (result.isSuccess()) {
                    System.out.println(result.getAccumulator());
                }
            }
        }
    }

    private static List<Instruction> generateCopyWithReplacement(List<Instruction> instructions, int i, Operation operation) {
        List<Instruction> newInstructions = new ArrayList<>(instructions);
        newInstructions.set(i, new Instruction(operation, instructions.get(i).getArgument()));
        return newInstructions;
    }
}
