package com.wpdough.passprocess.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PassportIdValidatorTest {
    @Test
    public void testPassportId() {
        Map<String, Boolean> samples = new HashMap<>();
        samples.put("000000001", true);
        samples.put("0123456789", false);

        FieldValidator validator = new PassportIdValidator();
        samples.forEach((value, expected) -> {
            boolean actual = validator.validate(value);
            Assertions.assertEquals(expected, actual, value);
        });
    }
}