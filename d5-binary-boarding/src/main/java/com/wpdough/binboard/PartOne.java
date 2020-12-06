package com.wpdough.binboard;

import java.io.IOException;

public class PartOne {

    public static void main(String[] args) throws IOException {
        String fileName = args[0];

        int highestSeatId = BoardingPassUtil.readBoardingPasses(fileName).stream()
                .mapToInt(BoardingPass::getSeatId)
                .max()
                .getAsInt();

        System.out.println(highestSeatId);
    }
}
