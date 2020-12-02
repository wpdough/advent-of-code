package com.wpdough.reportrepair;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class FileUtil {

    public static List<Integer> readIntegers(String fileName) throws IOException {
        return Files.lines(Paths.get(fileName))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
