package org.example.exceptions;

/**
 * Runtime exception that caused when user input doesn't match Yes|No-answer.
 */
public class NotYesNoAnswerException extends  RuntimeException{
    public NotYesNoAnswerException(String errorMessage) {
        super(errorMessage);
    }
}
