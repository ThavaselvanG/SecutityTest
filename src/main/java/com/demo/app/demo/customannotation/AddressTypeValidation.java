package com.demo.app.demo.customannotation;


import com.demo.app.demo.apis.accounts.AddressType;
import com.demo.app.demo.customannotation.handler.AddressTypeValidator;
import com.demo.app.demo.customannotation.handler.PasswordConstraintValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.Arrays;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = AddressTypeValidator.class)
@Target({TYPE, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
public @interface AddressTypeValidation {

    String message() default "Invalid Address type ";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
