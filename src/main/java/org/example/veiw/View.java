package org.example.veiw;

import java.util.BitSet;

public interface View {
    public void showMessage(String message);
    public void showMaskedWord(String word, BitSet mask);
    public String takeLetter();
    public boolean doYouWantToContinue();
}
