package org.example.veiw;

import java.util.BitSet;

public class ConsoleView implements View{
    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void showMaskedWord(String word, BitSet mask) {
        for (int i = 0; i < word.length(); i++) {
            if (mask.get(i))
                System.out.print(word.charAt(i));
            else
                System.out.print("*");
        }
        System.out.print("\n");
    }

    @Override
    public String takeLetter() {
        return "";
    }

    @Override
    public boolean doYouWantToContinue() {
        return false;
    }
}
