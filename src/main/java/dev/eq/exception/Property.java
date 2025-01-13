package dev.eq.exception;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Property extends FWException{
    public Property(String message) {
        super(message);
        Logger.getAnonymousLogger().warning(message);
    }

    public Property(String message, Throwable cause) {
        super(message, cause);
        Logger.getAnonymousLogger().log(Level.CONFIG,message,getCause());
    }
}
