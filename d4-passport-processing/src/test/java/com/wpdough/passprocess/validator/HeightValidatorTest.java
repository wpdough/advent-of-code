package com.wpdough.passprocess.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class HeightValidatorTest {

    @Test
    public void testHeight() {
        Map<String, Boolean> samples = new HashMap<>();
        samples.put("60in", true);
        samples.put("190cm", true);
        samples.put("190in", false);
        samples.put("190", false);

        FieldValidator validator = new HeightValidator();
        samples.forEach((value, expected) -> {
            boolean actual = validator.validate(value);
            Assertions.assertEquals(expected, actual, value);
        });
    }
}