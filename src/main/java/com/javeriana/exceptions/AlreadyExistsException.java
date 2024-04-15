package com.javeriana.exceptions;

/**
 * The AlreadyExistsException class is a custom exception that is thrown when an attempt is made to create an entity that already exists.
 * This could be used, for example, when trying to create a new user with a username that is already taken.
 */
public class AlreadyExistsException extends Exception {

    /**
     * Constructs a new AlreadyExistsException with the specified detail message.
     *
     * @param message the detail message. The detail message is saved for later retrieval by the Throwable.getMessage() method.
     */
    public AlreadyExistsException(String message) {
        super(message);
    }
}
