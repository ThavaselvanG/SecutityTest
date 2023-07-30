package com.demo.app.demo.customannotation;


import com.demo.app.demo.customannotation.handler.PasswordConstraintValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = PasswordConstraintValidator.class)
@Target({TYPE, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
public @interface NullEmptyHandler {
    String message() default "Not be null or empty";
     Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
