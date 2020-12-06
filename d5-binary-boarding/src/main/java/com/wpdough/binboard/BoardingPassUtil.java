package com.wpdough.binboard;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class BoardingPassUtil {

    public static List<BoardingPass> readBoardingPasses(String fileName) throws IOException {
        return Files.lines(Paths.get(fileName))
                .map(BoardingPass::parseString)
                .collect(Collectors.toList());
    }
}
