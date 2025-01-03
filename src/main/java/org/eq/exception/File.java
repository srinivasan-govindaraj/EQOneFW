package org.eq.exception;

public class File extends FWException{
    public File(String message) {
        super(message);
    }
    public File(String message, Throwable cause) {
        super(message, cause);
    }
}
