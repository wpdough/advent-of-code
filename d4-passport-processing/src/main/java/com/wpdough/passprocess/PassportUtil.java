package com.wpdough.passprocess;

import com.wpdough.passprocess.model.PassportField;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PassportUtil {

    public static List<Map<PassportField, String>> readPassports(String fileName) throws IOException {
        List<String> lines = Files.lines(Paths.get(fileName))
                .collect(Collectors.toList());

        return readPassportSingleLines(lines)
                .stream()
                .map(PassportUtil::parsePassport)
                .collect(Collectors.toList());
    }

    public static List<String> readPassportSingleLines(List<String> lines) {
        List<String> passportLines = new ArrayList<>();
        String current = "";
        for (String line : lines) {
            if (line.isEmpty()) {
                passportLines.add(current);
                current = "";
            } else {
                current += " " + line;
                current = current.trim();
            }
        }
        passportLines.add(current);

        return passportLines;
    }

    public static Map<PassportField, String> parsePassport(String line) {
        Map<PassportField, String> passport = new HashMap<>();
        String[] fieldKeyPairs = line.split(" ");
        for (String fieldKeyPair : fieldKeyPairs) {
            String[] parts = fieldKeyPair.split(":");
            PassportField field = PassportField.parse(parts[0]);
            String value = parts[1];
            passport.put(field, value);
        }
        return passport;
    }
}
