package org.eq.exception;

public class Property extends FWException{
    public Property(String message) {
        super(message);
    }

    public Property(String message, Throwable cause) {
        super(message, cause);
    }
}
