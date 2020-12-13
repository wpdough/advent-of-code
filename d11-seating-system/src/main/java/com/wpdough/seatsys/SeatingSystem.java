package com.wpdough.seatsys;

import com.wpdough.aoc.AocProblem;

import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SeatingSystem extends AocProblem<SeatingChart> {

    @Override
    public SeatingChart parseInput(List<String> input) {
        return new SeatingChart(input);
    }

    @Override
    public long partOne(SeatingChart chart) {

        while (applySeatingRules(chart) != 0) {
            chart.print();
        }

        return chart.countOccupiedSeats();
    }

    @Override
    public long partTwo(SeatingChart chart) {
        return 0;
    }

    private static int applySeatingRules(SeatingChart chart) {
        Map<Point, SpaceState> changes = new HashMap<>();
        for (int x = 0; x < chart.getColumns(); x++) {
            for (int y = 0; y < chart.getRows(); y++) {
                if (chart.getState(x, y).equals(SpaceState.EMPTY) && chart.countOccupiedAdj(x, y) == 0) {
                    changes.put(new Point(x, y), SpaceState.OCCUPIED);
                }

                if (chart.getState(x, y).equals(SpaceState.OCCUPIED) && chart.countOccupiedAdj(x, y) >= 4) {
                    changes.put(new Point(x, y), SpaceState.EMPTY);
                }
            }
        }

        changes.forEach((coord, state) -> chart.setState(coord.x, coord.y, state));

        return changes.size();
    }

    public static void main(String[] args) throws IOException {
        new SeatingSystem().solve(args[0]);
    }
}
