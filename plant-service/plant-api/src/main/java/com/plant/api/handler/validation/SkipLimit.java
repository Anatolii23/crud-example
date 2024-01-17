package com.plant.api.handler.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom annotation to check skip and limit.
 *
 * @author Anatolii Hamza
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SkipLimitValidator.class)
public @interface SkipLimit {

    String message() default "Skip cannot be negative and limit must have value between 1 and 50.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
