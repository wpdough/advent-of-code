package com.wpdough.passprocess.validator;

import com.wpdough.passprocess.model.EyeColor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EyeColorValidator implements FieldValidator {
    private static final List<String> VALID_COLORS = Arrays.stream(EyeColor.values())
            .map(EyeColor::getAbbreviation)
            .collect(Collectors.toList());

    @Override
    public boolean validate(String value) {
        return VALID_COLORS.contains(value);
    }
}
