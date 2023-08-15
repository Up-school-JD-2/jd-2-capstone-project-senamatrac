package io.upschool.exception;

public class TicketSoldOut extends Exception {
    public TicketSoldOut(String message) {
        super(message);
    }
}
