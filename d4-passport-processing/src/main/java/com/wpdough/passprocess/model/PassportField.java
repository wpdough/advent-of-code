package com.wpdough.passprocess.model;

import com.wpdough.passprocess.validator.*;

import java.util.Arrays;

public enum PassportField {
    BIRTH_YEAR      ("byr", new YearValidator(1920, 2002)),
    ISSUE_YEAR      ("iyr", new YearValidator(2010, 2020)),
    EXPIRATION_YEAR ("eyr", new YearValidator(2020, 2030)),
    HEIGHT          ("hgt", new HeightValidator()),
    HAIR_COLOR      ("hcl", new HairColorValidator()),
    EYE_COLOR       ("ecl", new EyeColorValidator()),
    PASSPORT_ID     ("pid", new PassportIdValidator()),
    COUNTRY_ID      ("cid", value -> true);

    private String key;
    private FieldValidator validator;

    PassportField(String key, FieldValidator validator) {
        this.key = key;
        this.validator = validator;
    }

    public static PassportField parse(String part) {
        return Arrays.stream(PassportField.values())
                .filter(field -> field.getKey().equals(part))
                .findAny()
                .get();
    }

    public String getKey() {
        return key;
    }

    public FieldValidator getValidator() {
        return validator;
    }
}
