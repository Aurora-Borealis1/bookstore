package com.gongyuan.bookstore.util.validation;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author: gongyuan
 * @date: 2024/8/10 19:45
 */
public class EnumNameValidator implements ConstraintValidator<ValidEnumName, String> {

    private ValidEnumName validEnumName;

    @Override
    public void initialize(ValidEnumName constraintAnnotation) {
        this.validEnumName = constraintAnnotation;
    }


    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (StringUtils.isBlank(value)) {
            return validEnumName.enableBlankStr();
        }

        try {
            Class<? extends Enum> clazz = validEnumName.value();
            Enum.valueOf(clazz, value);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }


}
