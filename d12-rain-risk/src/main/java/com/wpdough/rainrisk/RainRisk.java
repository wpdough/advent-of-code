package com.wpdough.rainrisk;

import com.wpdough.aoc.AocProblem;

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

        Ship ship = new Ship();
        Waypoint waypoint = new Waypoint();

        for (Instruction instruction : input) {
            Action action = instruction.getAction();
            int value = instruction.getValue();
            switch (action) {
                case NORTH:
                case SOUTH:
                case EAST:
                case WEST:
                    waypoint.move(Direction.valueOf(action.toString()), value);
                    break;
                case FORWARD:
                    int x = waypoint.getPositionRelative().x * value;
                    int y = waypoint.getPositionRelative().y * value;
                    ship.getPosition().translate(x, y);
                    break;
                case RIGHT:
                    waypoint.rotate(value);
                    break;
                case LEFT:
                    waypoint.rotate(-1 * value);
                    break;
            }
        }

        int eastWest = Math.abs(ship.getPosition().x);
        int northSouth = Math.abs(ship.getPosition().y);
        int manhattanDistance = eastWest + northSouth;

        return manhattanDistance;
    }

    public static void main(String[] args) throws IOException {
        new RainRisk().solve(args[0]);
    }
}
