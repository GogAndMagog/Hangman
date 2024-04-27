package org.hangman.veiw.Dialog;

import java.util.Scanner;

public class BooleanDialog implements Dialog<Boolean> {

    private String title;
    private String errorMessage;
    private String paramTrue;
    private String paramFalse;

    public BooleanDialog(String title, String errorMessage, String paramTrue, String paramFalse) {
        this.title = title;
        this.errorMessage = errorMessage;
        this.paramTrue = paramTrue;
        this.paramFalse = paramFalse;
    }

    @Override
    public Boolean input() {
        String input;
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println(title);
            input = sc.next();

            if (!input.equals(paramTrue) && !input.equals(paramFalse)) {
                System.out.println(errorMessage);
            } else if (input.equals(paramTrue))
                return true;
            else
                return false;
        }
    }
}
