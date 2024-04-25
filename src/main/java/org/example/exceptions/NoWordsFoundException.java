package org.example.exceptions;

/**
 * Runtime exception that caused when program cant read the file that contains words.
 */
public class NoWordsFoundException extends RuntimeException{
    public NoWordsFoundException(String errorMessage) {
        super(errorMessage);
    }
}
