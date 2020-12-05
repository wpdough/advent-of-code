package com.wpdough.passprocess.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class EyeColorValidatorTest {

    @Test
    public void testEyeColor() {
        Map<String, Boolean> samples = new HashMap<>();
        samples.put("brn", true);
        samples.put("wat", false);

        FieldValidator validator = new EyeColorValidator();
        samples.forEach((value, expected) -> {
            boolean actual = validator.validate(value);
            Assertions.assertEquals(expected, actual, value);
        });
    }
}
