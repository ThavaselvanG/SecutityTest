package com.demo.app.demo.customannotation;


import com.demo.app.demo.customannotation.handler.AddressTypeValidator;
import com.demo.app.demo.customannotation.handler.RolesTypeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = RolesTypeValidator.class)
@Target({TYPE, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
public @interface RolesTypeValidation {

    String message() default "Invalid Role type ";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
