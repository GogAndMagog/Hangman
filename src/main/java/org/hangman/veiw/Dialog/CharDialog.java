package org.hangman.veiw.Dialog;

import java.util.Scanner;

public class CharDialog implements Dialog<Character> {

    private String title;
    private String errorMessage;

    public CharDialog(String title, String errorMessage) {
        this.title = title;
        this.errorMessage = errorMessage;
    }

    @Override
    public Character input() {
        String input;
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println(title);
            input = sc.next();

            if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
                System.out.println(errorMessage);
            } else
                return input.charAt(0);
        }
    }
}
