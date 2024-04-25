package org.example.exceptions;

/**
 * Runtime exception, that caused when user input is not a single letter.
 */
public class NotLetterException extends RuntimeException{
    public NotLetterException(String errorMessage) {
        super(errorMessage);
    }
}
