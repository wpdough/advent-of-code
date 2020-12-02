package com.wpdough.pwphilosophy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class PartTwo {

    public static void main(String[] args) throws IOException {
        String fileName = args[0];
        List<String> fileLines = Files.lines(Paths.get(fileName))
                .collect(Collectors.toList());

        Map<PasswordPolicy, String> policyPasswordMap = new HashMap<>();
        fileLines.forEach(line -> {
            String[] parts = line.split(": ");
            PasswordPolicy passwordPolicy = PasswordPolicy.parseString(parts[0]);
            String password = parts[1];
            policyPasswordMap.put(passwordPolicy, password);
        });

        AtomicInteger numMatchingPasswords = new AtomicInteger();
        policyPasswordMap.forEach((policy, password) -> {
            boolean firstLetterMatch = password.charAt(policy.getA() - 1) == policy.getLetter();
            boolean secondLetterMatch = password.charAt(policy.getB() - 1) == policy.getLetter();
            if (firstLetterMatch ^ secondLetterMatch) {
                numMatchingPasswords.getAndIncrement();
            }
        });

        System.out.println(numMatchingPasswords.get());
    }
}
