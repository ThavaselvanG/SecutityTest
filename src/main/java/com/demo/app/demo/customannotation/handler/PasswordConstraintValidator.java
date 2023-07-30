package com.demo.app.demo.customannotation.handler;

import com.demo.app.demo.customannotation.ValidPassword;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.regex.Pattern;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

    int length = 0;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void initialize(ValidPassword constraintAnnotation) {
        length = constraintAnnotation.size();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (length > s.length()) {
            constraintValidatorContext.buildConstraintViolationWithTemplate("Password length should be 8 digit")
                    .addConstraintViolation().disableDefaultConstraintViolation();
            return false;
        }
        return isValidPassword(s);
    }

    private boolean isValidPassword(String s) {
         return Pattern.compile("((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{4,20})").matcher(s).matches();
    }
}
