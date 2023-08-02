package io.upschool.exception;

import java.util.function.Supplier;

public class DataNotFoundException extends Exception{
    public DataNotFoundException(String message) {
        super(message);
    }
}
