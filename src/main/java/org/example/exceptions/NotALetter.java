package org.example.exceptions;

public class NotALetter extends RuntimeException{
    public NotALetter(String errorMessage) {
        super(errorMessage);
    }
}
