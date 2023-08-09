package io.upschool.exception;

public class DataNotFoundException extends Exception {
    public DataNotFoundException(String field) {
        super("The " + field + " cannot found.");
    }

}
