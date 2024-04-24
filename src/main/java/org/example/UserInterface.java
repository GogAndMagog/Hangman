package org.example;

import org.example.exceptions.NotALetter;
import org.example.exceptions.NotYesNoAnswer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.BitSet;

import java.util.Scanner;
import java.util.regex.Pattern;


public class UserInterface {

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String... args) {
//        try {
//            System.out.println(UserInterface.takeALetter(""));
//        } catch (RuntimeException e) {
//            System.out.println(e.getMessage());
//            UserInterface.main();
//        }
        String testWord = "Shusha";
        BitSet mask = new BitSet(testWord.length());

        mask.set(0, testWord.length() - 1, false);
//        mask.set(0, true);
//        mask.set(1, true);
//        mask.set(2, true);
//        mask.set(3, true);
//        mask.set(4, true);
        mask.set(5, false);

        System.out.println(mask.length());
        System.out.println(mask.size());
        maskTheWord(testWord, mask);

    }

    public static String takeALetter(String userMessage) {
        if (userMessage == null || userMessage.isEmpty() || userMessage.isEmpty())
            userMessage = "Please enter a letter:";
        Scanner sc = new Scanner(System.in);
        Pattern singleLetterPattern = Pattern.compile("\\p{L}");

        System.out.println(userMessage);
        String entry = sc.next();

        //Validation.
        if (!singleLetterPattern.matcher(entry).matches()) {
            throw new NotALetter(entry + " is not a single letter!");
        }

        return entry;
    }

    public static String takeALetterRecursive()
    {
        try {
            return takeALetter("");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return takeALetterRecursive();
        }
    }

    public static boolean takeYesNoAnswer() {
        String userMessage = "Please enter Y/N:";
        Pattern yesNoPattern = Pattern.compile("[YyNn]");
        Pattern yesPattern = Pattern.compile("[Yy]");
        var answer = UserInterface.takeALetter(userMessage);

        //Validation.
        if (!yesNoPattern.matcher(answer).matches())
            throw new NotYesNoAnswer(answer + " is not Y/N answer!");

        if (yesPattern.matcher(answer).matches())
            return true;
        else
            return false;
    }

    public static boolean takeYesNoAnswerRecursive()
    {
        try {
            return takeYesNoAnswer();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return takeYesNoAnswerRecursive();
        }
    }

    public static void maskTheWord(String word, BitSet mask) {
        for (int i = 0; i < word.length(); i++) {
            if (mask.get(i) == true)
                System.out.print(word.charAt(i));
            else
                System.out.print("*");
        }
        System.out.print("\n");
    }

    public static void showMessage(String message) {
        System.out.println(message);
    }

    public static void paintGameState(GameState state) {
        System.out.println(state.view);
    }
}
