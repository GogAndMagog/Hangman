package org.example.exceptions;

public class NotYesNoAnswer extends  RuntimeException{
    public NotYesNoAnswer(String errorMessage) {
        super(errorMessage);
    }
}
