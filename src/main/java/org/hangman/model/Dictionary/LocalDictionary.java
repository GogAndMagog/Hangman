package org.hangman.model.Dictionary;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class LocalDictionary implements Dictionary{
    private String wordsResourcePath;
    private List<String> words;

    public LocalDictionary(String wordsResourcePath)
    {
        this.wordsResourcePath = wordsResourcePath;
        words = getWords();
    }

    private List<String> getWords() {
        List<String> words = new ArrayList<>();

        InputStream is = getClass().getClassLoader().getResourceAsStream(wordsResourcePath);
        if (is == null)
            return words;

        InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(streamReader);
        try {
            for (String line; (line = reader.readLine()) != null; ) {
                words.addAll(Arrays.stream(line.split("\\s+")).map(String::toLowerCase).toList());
            }

        } catch (Exception e) {
            return words;
        }

        return words;
    }

    @Override
    public String getWord() {
        int wordIndex;
        Random rand = new Random();
        wordIndex = rand.nextInt(0, words.size());
        return words.get(wordIndex);
    }

    @Override
    public boolean isEmpty() {
        return  words.isEmpty();
    }
}
