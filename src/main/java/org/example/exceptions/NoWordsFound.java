package org.example.exceptions;

public class NoWordsFound extends RuntimeException{
    public NoWordsFound(String errorMessage) {
        super(errorMessage);
    }
}
