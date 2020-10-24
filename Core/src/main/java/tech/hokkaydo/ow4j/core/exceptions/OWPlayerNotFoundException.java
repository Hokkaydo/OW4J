package tech.hokkaydo.ow4j.core.exceptions;

public class OWPlayerNotFoundException extends Exception{

    private final Cause cause;
    public OWPlayerNotFoundException(String message, Cause cause){
        super(message);
        this.cause = cause;
    }

    public Cause getErrorCause() {
        return cause;
    }

    public enum Cause {
        PROFILE_NOT_FOUND, INVALID_BATTLE_TAG, INTERNAL_ERROR
    }
}
