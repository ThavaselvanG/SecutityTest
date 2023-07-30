package com.demo.app.demo.customannotation.handler;

import com.demo.app.demo.apis.accounts.Roles;
import com.demo.app.demo.customannotation.AddressTypeValidation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RolesTypeValidator implements ConstraintValidator<AddressTypeValidation, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return enumList.contains(s);

    }

    private Set<String> enumList;

    @Override
    public void initialize(AddressTypeValidation constraintAnnotation) {
        enumList = Stream.of(Roles.values())
                .map(Enum::name)
                .collect(Collectors.toSet());
    }
}
