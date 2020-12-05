package com.wpdough.passprocess.validator;

public class YearValidator implements FieldValidator {
    private int minYear;
    private int maxYear;

    public YearValidator(int minYear, int maxYear) {
        this.minYear = minYear;
        this.maxYear = maxYear;
    }

    @Override
    public boolean validate(String value) {
        try {
            int year = Integer.parseInt(value);
            return value.length() == 4 && year >= minYear && year <= maxYear;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
