package io.upschool.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Constraint(validatedBy = UniqueValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueValidation {
    String message() default "The seat code should be unique.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
