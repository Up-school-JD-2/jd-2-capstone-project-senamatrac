package io.upschool.exception;

public class DuplicateEntryException extends Exception {
    public DuplicateEntryException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateEntryException(String message) {
        super(message);
    }
}
