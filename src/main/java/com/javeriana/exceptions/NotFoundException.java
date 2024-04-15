package com.javeriana.exceptions;

/**
 * The NotFoundException class is a custom exception that is thrown when an entity is not found.
 * This could be used, for example, when trying to retrieve a user, song, playlist, or artist by their ID and no match is found.
 */
public class NotFoundException extends Exception {

    /**
     * Constructs a new NotFoundException with the specified detail message.
     *
     * @param message the detail message. The detail message is saved for later retrieval by the Throwable.getMessage() method.
     */
    public NotFoundException(String message) {
        super(message);
    }
}
