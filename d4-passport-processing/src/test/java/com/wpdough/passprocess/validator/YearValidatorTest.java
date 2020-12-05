package com.wpdough.passprocess.validator;

import com.wpdough.passprocess.model.PassportField;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class YearValidatorTest {

    @Test
    public void testYear() {
        Map<String, Boolean> samples = new HashMap<>();
        samples.put("2002", true);
        samples.put("2003", false);
        samples.put("15555", false);

        FieldValidator validator = PassportField.BIRTH_YEAR.getValidator();
        samples.forEach((value, expected) -> {
            boolean actual = validator.validate(value);
            Assertions.assertEquals(expected, actual, value);
        });
    }
}
