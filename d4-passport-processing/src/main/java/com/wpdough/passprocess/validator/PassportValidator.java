package com.wpdough.passprocess.validator;

import com.wpdough.passprocess.model.PassportField;

import java.util.Map;

public interface PassportValidator {

    boolean isValid(Map<PassportField, String> passport);

}
