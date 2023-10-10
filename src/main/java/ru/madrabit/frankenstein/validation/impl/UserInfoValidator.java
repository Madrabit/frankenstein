package ru.madrabit.frankenstein.validation.impl;

import ru.madrabit.frankenstein.dto.UserCreateEditDto;
import ru.madrabit.frankenstein.validation.UserInfo;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static org.springframework.util.StringUtils.hasText;

public class UserInfoValidator implements ConstraintValidator<UserInfo, UserCreateEditDto> {
    @Override
    public boolean isValid(UserCreateEditDto value, ConstraintValidatorContext context) {
        return hasText(value.getFirstname()) || hasText(value.getLastname());
    }
}
