package org.example;

/**
 * It starts everything. Alpha and Omega.
 */
public class Main {
    /**
     * Program entry point.
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        try {
            Hangman hangman = new Hangman();
            hangman.start();
        } catch (Exception e) {
            UserInterface.showMessage(e.getMessage());
        }
    }
}