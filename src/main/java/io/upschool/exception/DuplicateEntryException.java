package io.upschool.exception;

public class DuplicateEntryException extends Exception {
    public DuplicateEntryException(String field) {
        super("The field " +field+ " must be unique");
    }
}
