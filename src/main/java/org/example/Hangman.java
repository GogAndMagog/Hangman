package org.example;

import org.example.exceptions.NoWordsFoundException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * Kinda controller. It analyzes correctness of user answers and controls game progress.
 */
public class Hangman {

    /**
     * Path to file with words.
     */
    private final String WORDS_RESOURCE_PATH = "Words";

    /**
     * Game state that represents the game progress.
     */
    private GameState state;

    /**
     * Exit flag. If this is true - the program will exit.
     */
    private boolean exit;

    /**
     * Collection of words.
     */
    private final List<String> words;

    /**
     * Current word that user must guess.
     */
    private String secretWord;

    /**
     * Contains positions of correct answered letters in the word.
     */
    private BitSet mask;

    /**
     * Standard constructor without parameters.
     */
    Hangman() {
        state = GameState.ERRORS_0;
        exit = false;
        words = getWords();
    }

    /**
     * Let the battle BEGIN!!!
     */
    public void start() {
        int wordIndex;
        Random rand = new Random();

        UserInterface.showMessage("Do you want to continue?");
        exit = !UserInterface.takeYesNoAnswerRecursive();

        while (!exit) {
            wordIndex = rand.nextInt(0, words.size());
            secretWord = words.get(wordIndex);
            mask = new BitSet();
            mask.set(0, secretWord.length() - 1, false);
            mask.set(secretWord.length(), true);

            while (state != GameState.ERRORS_5 && state != GameState.WIN) {
                UserInterface.maskWord(secretWord, mask);
                if (isCorrectLetter(UserInterface.takeALetter(), secretWord, mask)) {
                    UserInterface.showMessage("Correct!");
                    if (isAnswered(mask)) {
                        state = GameState.WIN;
                        UserInterface.paintGameState(state);
                    }
                } else {
                    UserInterface.showMessage("WRONG!");

                    switch (state) {
                        case GameState.ERRORS_0:
                            state = GameState.ERRORS_1;
                            break;
                        case GameState.ERRORS_1:
                            state = GameState.ERRORS_2;
                            break;
                        case GameState.ERRORS_2:
                            state = GameState.ERRORS_3;
                            break;
                        case GameState.ERRORS_3:
                            state = GameState.ERRORS_4;
                            break;
                        case GameState.ERRORS_4:
                            state = GameState.ERRORS_5;
                            break;
                        default:
                            break;
                    }

                    UserInterface.paintGameState(state);
                }
            }

            UserInterface.showMessage("Do you want to continue?");
            exit = !UserInterface.takeYesNoAnswerRecursive();
            state = GameState.ERRORS_0;
        }
    }

    /**
     * Getting the words-collection from resources.
     *
     * @return list of words.
     */
    private List<String> getWords() {
        List<String> words = new ArrayList<>();

        InputStream is = getClass().getClassLoader().getResourceAsStream(WORDS_RESOURCE_PATH);
        if (is == null)
            throw new NoWordsFoundException("Can't found any words, please check \"resources\" folder!");

        InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(streamReader);
        try {
            for (String line; (line = reader.readLine()) != null; ) {
                words.addAll(Arrays.stream(line.split("\\s+")).toList());
            }

        } catch (Exception e) {
            throw (NoWordsFoundException)
                    new NoWordsFoundException("Can't found any words, please check \"resources\" folder!")
                            .initCause(e);
        }

        return words;
    }

    /**
     * Checks if word contains that letter and if so - changes the mask.
     *
     * @param letter user answer.
     * @param word   secret word.
     * @param mask   mask......
     * @return true if answer is correct, false if not.
     */
    private boolean isCorrectLetter(String letter, String word, BitSet mask) {
        boolean result = false;
        for (int i = 0; i < word.length(); i++)
            if (letter.charAt(0) == word.charAt(i)) {
                mask.set(i, true);
                result = true;
            }

        return result;
    }

    /**
     * Checks if the riddle is solved. If all bits in mask are ture then the riddle is solved.
     *
     * @param mask mask...
     * @return true if the riddle is solved, false if not.
     */
    private boolean isAnswered(BitSet mask) {
        for (int i = 0; i < mask.length() - 1; i++)
            if (!mask.get(i))
                return false;

        return true;
    }
}
