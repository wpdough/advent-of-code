package com.wpdough.passprocess.validator;

public class HeightValidator implements FieldValidator {

    @Override
    public boolean validate(String value) {
        if (!value.endsWith("cm") && !value.endsWith("in"))
            return false;

        int min = value.endsWith("cm") ? 150 : 59;
        int max = value.endsWith("cm") ? 193 : 76;

        try {
            String cleaned = value.replace("cm", "").replace("in", "");
            int measurement = Integer.parseInt(cleaned);
            return measurement >= min && measurement <= max;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
}
