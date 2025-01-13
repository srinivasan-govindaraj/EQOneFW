package dev.eq.exception;

import java.util.logging.Level;
import java.util.logging.Logger;

public class FWException extends RuntimeException{
    public FWException(String message)
    {
        super(message);
        Logger.getAnonymousLogger().warning(message);
    }
    public FWException(String message,Throwable cause)
    {
        super(message,cause);
        Logger.getAnonymousLogger().log(Level.SEVERE,message,getCause());
    }

}
