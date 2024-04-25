package org.example;

import org.example.controller.Hangman;

public class Main {
    public static void main(String[] args) {
        try {
            Hangman hangman = new Hangman();
            hangman.start();
        } catch (Exception e) {
            UserInterface.showMessage(e.getMessage());
        }
    }
}