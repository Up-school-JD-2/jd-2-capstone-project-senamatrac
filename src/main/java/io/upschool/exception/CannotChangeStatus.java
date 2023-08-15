package io.upschool.exception;

public class CannotChangeStatus extends RuntimeException {
    public CannotChangeStatus(String message) {
        super(message);
    }
}
