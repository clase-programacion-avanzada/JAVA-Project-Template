package com.javeriana.exceptions;

/**
 * The WrongLogInException class is a custom exception that is thrown when a login attempt fails.
 * This could be used, for example, when a user tries to log in with incorrect credentials.
 */
public class WrongLogInException extends Throwable {

    /**
     * Constructs a new WrongLogInException with the specified detail message.
     *
     * @param message the detail message. The detail message is saved for later retrieval by the Throwable.getMessage() method.
     */
    public WrongLogInException(String message) {
        super(message);
    }
}
