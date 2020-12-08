package com.wpdough.handhalt;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class PartOne {

    public static void main(String[] args) throws IOException {
        String fileName = args[0];
        List<Instruction> instructions = Files.lines(Paths.get(fileName))
                .map(Instruction::parse)
                .collect(Collectors.toList());

        int accumulator = InstructionRunner.execute(instructions).getAccumulator();
        System.out.println(accumulator);
    }
}
