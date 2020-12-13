package com.wpdough.rainrisk;

import com.wpdough.aoc.AocProblem;

import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class RainRisk extends AocProblem<List<Instruction>> {
    @Override
    public List<Instruction> parseInput(List<String> input) {
        return input.stream()
                .map(Instruction::parse)
                .collect(Collectors.toList());
    }

    @Override
    public long partOne(List<Instruction> input) {

        Ship ship = new Ship();
        input.forEach(ship::move);

        int eastWest = Math.abs(ship.getPosition().x);
        int northSouth = Math.abs(ship.getPosition().y);
        int manhattanDistance = eastWest + northSouth;

        return manhattanDistance;
    }

    @Override
    public long partTwo(List<Instruction> input) {
        return 0;
    }

    public static void main(String[] args) throws IOException {
        new RainRisk().solve(args[0]);
    }
}
