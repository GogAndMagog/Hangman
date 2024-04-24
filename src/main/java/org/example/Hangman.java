package org.example;

import org.example.exceptions.NoWordsFound;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Hangman{

    private final String WORDS_RESOURCE_PATH = "Words";
    private GameState state;
    private boolean exit;
    private List<String> words;
    private String secretWord;
    private BitSet mask;

    Hangman() {
        state = GameState.ERRORS_0;
        exit = false;
        words = getWords();
    }

    public static void main(String... args) {
        Hangman hm = new Hangman();
        try {
            var words = hm.getWords();
            words.forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Can't find any words, try to check \"resouces\" folder!");
        }
    }

    public void start() {
        int wordIndex = 0;
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
                UserInterface.maskTheWord(secretWord, mask);
                if (isCorrectLetter(UserInterface.takeALetterRecursive(), secretWord, mask)) {
                    UserInterface.showMessage("Correct!");
                    if (isAnswered(mask))
                        state = GameState.WIN;
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

            UserInterface.paintGameState(state);

            UserInterface.showMessage("Do you want to continue?");
            exit = !UserInterface.takeYesNoAnswerRecursive();
            state = GameState.ERRORS_0;
        }
    }

    private List<String> getWords() {
        List<String> words = new ArrayList<>();

        InputStream is = getClass().getClassLoader().getResourceAsStream(WORDS_RESOURCE_PATH);
        InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(streamReader);
        try {
            for (String line; (line = reader.readLine()) != null; ) {
                words.addAll(Arrays.stream(line.split("\\s+")).toList());
            }

        } catch (Exception e) {
            throw (NoWordsFound)
                    new NoWordsFound("Can't found any words, please check \"resources\" folder!")
                            .initCause(e);
        }

        return words;
    }

    private boolean isCorrectLetter(String letter, String word, BitSet mask) {
        boolean result = false;
        for (int i = 0; i < word.length(); i++)
            if (letter.charAt(0) == word.charAt(i)) {
                mask.set(i, true);
                result = true;
            }

        return result;
    }

    private boolean isAnswered(BitSet mask) {
        for (int i = 0; i < mask.length() - 1; i++)
            if (!mask.get(i))
                return false;

        return true;
    }
}
