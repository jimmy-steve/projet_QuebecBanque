package com.example.projet_quebecbanque.controller.exception;

public class CarteNotFoundException extends Exception{
    /**
     * Constructs a new exception with {@code null} as its detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     */
    public CarteNotFoundException(String message) {
        super(message);
    }
}
