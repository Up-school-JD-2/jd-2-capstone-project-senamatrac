package io.upschool.exception;

import java.util.function.Predicate;
import java.util.function.Supplier;

public class ServiceExceptionUtil {
    public static  <T,E extends Exception> void check(Predicate<T> func, T field, Supplier<E> exceptionSupplier) throws E {
        if (func.test(field)){
            throw exceptionSupplier.get();
        }
    }
    public static  <T,E extends Exception> void check(Supplier<T> func, Supplier<E> exceptionSupplier) throws E {
        if (func.get() instanceof Boolean && (Boolean) func.get()){
            throw exceptionSupplier.get();
        }
    }
}
