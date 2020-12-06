package com.wpdough.binboard;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PartTwo {

    public static void main(String[] args) throws IOException {
        String fileName = args[0];

        List<BoardingPass> inputPasses = BoardingPassUtil.readBoardingPasses(fileName);
        List<BoardingPass> missingPasses = calculateAllPossiblePasses().stream()
                .filter(pass -> !inputPasses.contains(pass) && passesContainOccupiedSeatsNextTo(inputPasses, pass))
                .collect(Collectors.toList());

        missingPasses.forEach(System.out::println);
    }

    private static boolean passesContainOccupiedSeatsNextTo(List<BoardingPass> inputPasses, BoardingPass seat) {
        return inputPasses.contains(new BoardingPass(seat.getSeatId() - 1)) &&
                inputPasses.contains(new BoardingPass(seat.getSeatId() + 1));
    }

    private static List<BoardingPass> calculateAllPossiblePasses() {
        List<BoardingPass> passes = new ArrayList<>();
        for (int row = 0; row < 128; row++) {
            for (int col = 0; col < 8; col++) {
                passes.add(new BoardingPass(row, col));
            }
        }
        return passes;
    }
}
