package io.upschool.exception;

import java.util.function.Supplier;

public class DataNotFoundException extends Exception{
    public DataNotFoundException(String field) {
        super("The "+field+" cannot found.");
    }

}
