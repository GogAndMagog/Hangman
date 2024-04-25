package org.example;

import org.example.exceptions.NotALetterException;
import org.example.exceptions.NotYesNoAnswerException;
import org.example.records.ValueExceptionRecord;

import java.util.BitSet;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Class for interaction with user. Kinda view.
 * Because it has no state all methods are static.
 */
public class UserInterface {

    /**
     * Service method that takes one user input and validate it.
     * @param userMessage information message that shown before user input.
     * @return user input can contain only one value or exception. If value is not Null, then exception is Null.
     * Conversely, if exception is not Null, then value is Null.
     */
    private static ValueExceptionRecord<String> takeLetter(String userMessage) {
        if (userMessage == null || userMessage.isEmpty() || userMessage.isBlank())
            userMessage = "Please enter a value:";
        Scanner sc = new Scanner(System.in);
        Pattern singleLetterPattern = Pattern.compile("\\p{L}");

        System.out.println(userMessage);
        String entry = sc.next();

        //Validation.
        if (!singleLetterPattern.matcher(entry).matches()) {
            return new ValueExceptionRecord(null, new NotALetterException(entry + " is not a single value!"));
        }

        return new ValueExceptionRecord(entry, null);
    }


    /**
     * Getting the letter from user.
     * @return one single letter.
     */
    public static String takeALetterRecursive() {
        ValueExceptionRecord<String> letterRecord = new ValueExceptionRecord(null, null);

        while (letterRecord.value() == null) {
            letterRecord = takeLetter("");
            if (letterRecord.e() != null)
                System.out.println(letterRecord.e().getMessage());
        }

        return letterRecord.value();
    }

    /**
     * Service method that takes one user input and validate is it Yy or Nn.
     * @return user answer can contain only one value or exception. If value is not Null, then exception is Null.
     * Conversely, if exception is not Null, then value is Null.
     */
    private static ValueExceptionRecord<Boolean> takeYesNoAnswer() {
        String userMessage = "Please enter Y/N:";
        Pattern yesNoPattern = Pattern.compile("[YyNn]");
        Pattern yesPattern = Pattern.compile("[Yy]");

        ValueExceptionRecord<String> letter = UserInterface.takeLetter(userMessage);
        if (letter.e() != null)
            return new ValueExceptionRecord<Boolean>(null, letter.e());

        //Validation.
        if (!yesNoPattern.matcher(letter.value()).matches())
            return new ValueExceptionRecord<Boolean>(null, new NotYesNoAnswerException(letter.value() + " is not Y/N answer!"));

        return new ValueExceptionRecord<Boolean>(yesPattern.matcher(letter.value()).matches() ? true : false, null);
    }

    /**
     * Asks the user if he wants to continue.
     * @return user answer.
     */
    public static boolean takeYesNoAnswerRecursive() {
        ValueExceptionRecord<Boolean> answer = new ValueExceptionRecord(null, null);

        while (answer.value() == null) {
            answer = takeYesNoAnswer();
            if (answer.e() != null)
                System.out.println(answer.e().getMessage());
        }

        return answer.value();
    }

    /**
     * Shows user the masked word.
     * @param word secret word.
     * @param mask used to hold positions of answered letters.
     */
    public static void maskTheWord(String word, BitSet mask) {
        for (int i = 0; i < word.length(); i++) {
            if (mask.get(i))
                System.out.print(word.charAt(i));
            else
                System.out.print("*");
        }
        System.out.print("\n");
    }

    /**
     * Shows user information messages.
     * @param message information message.
     */
    public static void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Prints the hanged man, depends on current game state.
     * @param state game state.
     */
    public static void paintGameState(GameState state) {
        System.out.println(state.getView());
    }

}
