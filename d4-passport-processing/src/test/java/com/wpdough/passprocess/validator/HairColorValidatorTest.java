package com.wpdough.passprocess.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class HairColorValidatorTest {

    @Test
    public void testHairColor() {
        Map<String, Boolean> samples = new HashMap<>();
        samples.put("#123abc", true);
        samples.put("#123abz", false);
        samples.put("123abc", false);

        FieldValidator validator = new HairColorValidator();
        samples.forEach((value, expected) -> {
            boolean actual = validator.validate(value);
            Assertions.assertEquals(expected, actual, value);
        });
    }
}