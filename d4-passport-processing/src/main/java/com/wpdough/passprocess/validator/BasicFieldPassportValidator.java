package com.wpdough.passprocess.validator;

import com.wpdough.passprocess.model.PassportField;

import java.util.Arrays;
import java.util.Map;

public class BasicFieldPassportValidator implements PassportValidator {
    public static final PassportField[] OPTIONAL_FIELDS = { PassportField.COUNTRY_ID };

    @Override
    public boolean isValid(Map<PassportField, String> passport) {
        for (PassportField field : PassportField.values()) {
            if (!passport.containsKey(field) && Arrays.stream(OPTIONAL_FIELDS).noneMatch(f -> f.equals(field))) {
                return false;
            }
        }

        return true;
    }
}
