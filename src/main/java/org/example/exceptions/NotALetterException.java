package org.example.exceptions;

/**
 * Runtime exception, that caused when user input is not a single letter.
 */
public class NotALetterException extends RuntimeException{
    public NotALetterException(String errorMessage) {
        super(errorMessage);
    }
}
