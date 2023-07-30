package com.demo.app.demo.customannotation.handler;

import com.demo.app.demo.apis.accounts.AddressType;
import com.demo.app.demo.customannotation.AddressTypeValidation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AddressTypeValidator implements ConstraintValidator<AddressTypeValidation, Integer> {

    @Override
    public boolean isValid(Integer s, ConstraintValidatorContext constraintValidatorContext) {
        return enumList.contains(s);

    }

    private Set<Integer> enumList;

    @Override
    public void initialize(AddressTypeValidation constraintAnnotation) {
        enumList = Stream.of(AddressType.values())
                .map(Enum::ordinal)
                .collect(Collectors.toSet());
    }
}
