package org.hangman.veiw.Dialog;

import java.util.BitSet;

public class ConsoleInfo implements Info {
    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void showMaskedMessage(String message, BitSet mask) {
        for (int i = 0; i < message.length(); i++) {
            if (mask.get(i))
                System.out.print(message.charAt(i));
            else
                System.out.print("*");
        }
        System.out.print("\n");
    }
}
