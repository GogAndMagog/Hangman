package org.example.model;

import org.example.exceptions.NoWordsFoundException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LocalDictionary implements Dictionary{
    private final String WORDS_RESOURCE_PATH = "Words";

    @Override
    public List<String> getWords() {
        List<String> words = new ArrayList<>();

        InputStream is = getClass().getClassLoader().getResourceAsStream(WORDS_RESOURCE_PATH);
        if (is == null)
            return words;

        InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(streamReader);
        try {
            for (String line; (line = reader.readLine()) != null; ) {
                words.addAll(Arrays.stream(line.split("\\s+")).toList());
            }

        } catch (Exception e) {
            return words;
        }

        return words;
    }
}
