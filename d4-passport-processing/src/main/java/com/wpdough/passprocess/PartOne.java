package com.wpdough.passprocess;

import com.wpdough.passprocess.validator.BasicFieldPassportValidator;
import com.wpdough.passprocess.validator.PassportValidator;

import java.io.IOException;

public class PartOne {

    public static void main(String[] args) throws IOException {
        String fileName = args[0];

        PassportValidator passportValidator = new BasicFieldPassportValidator();

        long validPassportCount = PassportUtil.readPassports(fileName).stream()
                .map(passportValidator::isValid)
                .filter(value -> value == true)
                .count();

        System.out.println(validPassportCount);
    }
}
