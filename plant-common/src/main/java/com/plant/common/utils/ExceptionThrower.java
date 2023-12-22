package com.plant.common.utils;

import com.plant.common.model.ServiceException;

import java.util.function.Supplier;

/**
 * Exception Thrower.
 *
 * @author Anatolii Hamza
 */
public final class ExceptionThrower {

    private ExceptionThrower() {
    }

    public static <T extends ServiceException> void throwsIf(boolean condition, Supplier<T> exceptionSupplier) throws T {
        if (condition) {
            throw exceptionSupplier.get();
        }
    }
}
