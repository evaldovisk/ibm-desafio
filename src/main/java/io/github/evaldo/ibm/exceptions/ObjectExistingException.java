package io.github.evaldo.ibm.exceptions;

public class ObjectExistingException extends RuntimeException {

    public ObjectExistingException(String message) { super(message); }

    public ObjectExistingException(String message, Throwable cause) { super(message, cause); }
}
