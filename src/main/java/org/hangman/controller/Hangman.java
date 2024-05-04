package org.hangman.controller;

import org.hangman.model.Dictionary.Dictionary;
import org.hangman.model.Dictionary.TestDictionary;
import org.hangman.model.DomainObjects.HangedMan;
import org.hangman.model.ImageProvider.AsciiImageProvider;
import org.hangman.model.ImageProvider.ImageProvider;
import org.hangman.veiw.Dialog.*;
import org.hangman.veiw.Render.ConsoleAsciiRenderer;
import org.hangman.veiw.Render.Renderer;

import java.util.*;

public class Hangman {

    private final String WORDS_RESOURCE_PATH = "dictionary/Words";
    private final String ASCII_IMAGES_RESOURCE_PATH = "images/ascii_images";
    private final String ASCII_IMAGES_SMALL_RESOURCE_PATH = "images/ascii_images_small";

    private final String CHARACTER_DIALOG_TITLE = "Please enter a letter: ";
    private final String CHARACTER_DIALOG_ERROR_MESSAGE = "Input is not a single letter!";

    private final String BOOLEAN_DIALOG_TITLE = "Do you want to continue? (Y/N)";
    private final String BOOLEAN_DIALOG_ERROR_MESSAGE = "Input is not (Y/N)!";
    private final String BOOLEAN_DIALOG_TRUE_PARAMETER = "Y";
    private final String BOOLEAN_DIALOG_PARAM_FALSE = "N";

    private final String USER_CORRECT_INPUT = "Correct!";
    private final String USER_WRONG_INPUT = "WRONG!";
    private final String USER_REPEATED_INPUT = "Repeating letter!";
    private final String NO_WORDS_FOUND = "No words found, sorry...";

    private final int HEALTH_POINTS = 5;

    private String secretWord;
    private BitSet mask;
    private Set<Character> repeatedLetters = new HashSet<>();

    private boolean goOn;
    private HangedMan<String> hangedMan;

    private Dictionary dictionary;
    private ImageProvider<String> imageProvider;

    private Dialog<Character> characterDialog;
    private Dialog<Boolean> booleanDialog;
    private Info info;
    private Renderer<String> renderer;

    public Hangman() {
        goOn = true;

//        dictionary = new LocalDictionary(WORDS_RESOURCE_PATH);
        dictionary = new TestDictionary();

        characterDialog = new CharDialog(CHARACTER_DIALOG_TITLE, CHARACTER_DIALOG_ERROR_MESSAGE);
        booleanDialog = new BooleanDialog(BOOLEAN_DIALOG_TITLE, BOOLEAN_DIALOG_ERROR_MESSAGE,
                BOOLEAN_DIALOG_TRUE_PARAMETER, BOOLEAN_DIALOG_PARAM_FALSE);
        info = new ConsoleInfo();
        renderer = new ConsoleAsciiRenderer();
    }

    public void start() {

        char input;

        if (dictionary.isEmpty()) {
            info.showMessage(NO_WORDS_FOUND);
            return;
        }

        goOn = booleanDialog.input();

        while (goOn) {
            secretWord = dictionary.getWord();
            mask = initMask(secretWord);
            imageProvider = new AsciiImageProvider(ASCII_IMAGES_SMALL_RESOURCE_PATH);
            hangedMan = new HangedMan<>(imageProvider, HEALTH_POINTS);
            repeatedLetters.clear();

            while (hangedMan.isAlive()) {
                info.showMaskedMessage(secretWord, mask);
                input = characterDialog.input();

                if (repeatedLetters.contains(input)) {
                    info.showMessage(USER_REPEATED_INPUT);
                    continue;
                }
                else
                    repeatedLetters.add(input);

                if (isCorrectLetter(input, secretWord)) {
                    updateMask(input, secretWord, mask);
                    info.showMessage(USER_CORRECT_INPUT);

                    if (isAnswered(mask)) {
                        hangedMan.release();
                        renderer.render(hangedMan.getImage());
                    }
                } else {
                    info.showMessage(USER_WRONG_INPUT);
                    hangedMan.doWorse();

                    renderer.render(hangedMan.getImage());
                }
            }

            goOn = booleanDialog.input();
        }
    }

    private BitSet initMask(String secretWord) {
        BitSet initialMask = new BitSet();
        initialMask.set(0, secretWord.length() - 1, false);
        initialMask.set(secretWord.length(), true);
        return initialMask;
    }

    private void updateMask(char letter, String word, BitSet mask) {
        for (int i = 0; i < word.length(); i++)
            if (letter == word.charAt(i)) {
                mask.set(i, true);
            }
    }

    private boolean isAnswered(BitSet mask) {
        for (int i = 0; i < mask.length() - 1; i++)
            if (!mask.get(i))
                return false;

        return true;
    }

    private boolean isCorrectLetter(char letter, String word) {
        for (int i = 0; i < word.length(); i++)
            if (letter == word.charAt(i))
                return true;

        return false;
    }

}
