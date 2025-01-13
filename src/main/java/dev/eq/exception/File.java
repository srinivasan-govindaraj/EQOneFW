package dev.eq.exception;

import java.util.logging.Level;
import java.util.logging.Logger;

public class File extends FWException{
    public File(String message) {
        super(message);
        Logger.getAnonymousLogger().warning(message);
    }
    public File(String message, Throwable cause) {
        super(message, cause);
        Logger.getAnonymousLogger().log(Level.INFO,message,getCause());
    }
}
