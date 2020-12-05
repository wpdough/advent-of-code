package com.wpdough.passprocess.validator;

public class HairColorValidator implements FieldValidator {
    private static final String HEX_PATTERN = "[0-9A-F]+";

    @Override
    public boolean validate(String value) {
        return value.length() == 7 &&
                value.startsWith("#") &&
                value.replace("#", "")
                        .toUpperCase()
                        .matches(HEX_PATTERN);
    }
}
