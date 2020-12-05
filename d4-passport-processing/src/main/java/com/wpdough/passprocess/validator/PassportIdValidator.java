package com.wpdough.passprocess.validator;

public class PassportIdValidator implements FieldValidator {
    @Override
    public boolean validate(String value) {
        try {
            int id = Integer.parseInt(value);
            return value.length() == 9;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
