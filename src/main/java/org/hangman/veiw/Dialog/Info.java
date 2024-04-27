package org.hangman.veiw.Dialog;

import java.util.BitSet;

public interface Info {
    public void showMessage(String message);
    public void showMaskedMessage(String message, BitSet mask);
}
