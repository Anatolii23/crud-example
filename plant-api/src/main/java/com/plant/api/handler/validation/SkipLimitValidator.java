package com.plant.api.handler.validation;

import com.plant.common.model.PaginationParams;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Custom validator - checks skip and limit values.
 *
 * @author Anatolii Hamza
 */
public class SkipLimitValidator implements ConstraintValidator<SkipLimit, Object> {

    @Override
    public void initialize(SkipLimit skipLimit) {
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        if (!(object instanceof PaginationParams)) {
            return false;
        }
        var paginationParams = (PaginationParams) object;
        return paginationParams.getLimit() != null &&
                paginationParams.getSkip() != null &&
                paginationParams.getSkip() >= 0 &&
                paginationParams.getLimit() > 0 &&
                paginationParams.getLimit() <= 50;
    }
}
