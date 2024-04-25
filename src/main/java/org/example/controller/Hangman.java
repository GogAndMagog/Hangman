package org.example.controller;

import org.example.GameProgress;
import org.example.UserInterface;
import org.example.exceptions.NoWordsFoundException;
import org.example.model.Dictionary;
import org.example.model.LocalDictionary;
import org.example.veiw.ConsoleView;
import org.example.veiw.View;

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
    private GameProgress state;
    private boolean exit;
    private final List<String> words;
    private String secretWord;

    Dictionary dictionary = new LocalDictionary();
    View view = new ConsoleView();

    /**
     * Contains positions of correct answered letters in the word.
     */
    private BitSet mask;

    public Hangman() {
        state = GameProgress.PICTURE_0;
        exit = false;
        dictionary = new LocalDictionary();
        words = dictionary.getWords();
    }

    public void start() {

        if (words.isEmpty()) {
            view.showMessage("No words found, sorry...");
            return;
        }

        UserInterface.showMessage("Do you want to continue?");
        exit = !UserInterface.takeYesNoAnswerRecursive();

        while (!exit) {
            secretWord = takeRandomWord(words);
            mask = initMask(secretWord);

            while (state != GameProgress.PICTURE_5 && state != GameProgress.PICTURE_6) {
                UserInterface.maskWord(secretWord, mask);
                if (isCorrectLetter(UserInterface.takeALetter(), secretWord, mask)) {
                    UserInterface.showMessage("Correct!");
                    if (isAnswered(mask)) {
                        state = GameProgress.PICTURE_6;
                        UserInterface.paintGameState(state);
                    }
                } else {
                    UserInterface.showMessage("WRONG!");

                    int step = state.ordinal() + 1;
                    state = state.values()[step];

                    UserInterface.paintGameState(state);
                }
            }

            UserInterface.showMessage("Do you want to continue?");
            exit = !UserInterface.takeYesNoAnswerRecursive();
            state = GameProgress.PICTURE_0;
        }
    }

    private String takeRandomWord(List<String> words) {
        int wordIndex;
        Random rand = new Random();
        wordIndex = rand.nextInt(0, words.size());
        return words.get(wordIndex);
    }

    private BitSet initMask(String secretWord)
    {
        BitSet initialMask = new BitSet();
        initialMask.set(0, secretWord.length() - 1, false);
        initialMask.set(secretWord.length(), true);
        return initialMask;
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
