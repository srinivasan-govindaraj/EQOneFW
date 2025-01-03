package org.eq.exception;

public class FWException extends RuntimeException{
    public FWException(String message)
    {
        super(message);
    }
    public FWException(String message,Throwable cause)
    {
        super(message,cause);
    }

}
