package org.hangman.model.Dictionary;

public class TestDictionary implements Dictionary{
   private String testWord = "лол";

    @Override
    public String getWord() {
        return testWord;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
