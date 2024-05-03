package org.hangman.model.ImageProvider;

import java.awt.*;
import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Filter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.stream.Stream;

public class AsciiImageProvider implements ImageProvider<String> {

    private final String DUMMY_IMAGE =
            "| \\ | | ___   (_)_ __ ___   __ _  __ _  ___| |\n" +
                    "|  \\| |/ _ \\  | | '_ ` _ \\ / _` |/ _` |/ _ \\ |\n" +
                    "| |\\  | (_) | | | | | | | | (_| | (_| |  __/_|\n" +
                    "|_| \\_|\\___/  |_|_| |_| |_|\\__,_|\\__, |\\___(_)\n" +
                    "                                 |___/        ";

    private List<String> asciiImages;
    private int index;

    public AsciiImageProvider(String resourcePath) {

        asciiImages = new ArrayList<>();

        loadImages(resourcePath);

        if (asciiImages.size() > 2)
            index = 1;
    }

    private void loadImages(String resourcePath){
        Path path = null;

        try {
            path = Path.of(AsciiImageProvider.class.getClassLoader().getResource(resourcePath).toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        if (path == null)
            return;

        try {
            Files.walk(path)
                    .filter(fileName ->
                            fileName.toString().endsWith(".txt")
                            )
                    .forEach(fileName -> {
                        try {
                            BufferedReader br = Files.newBufferedReader(fileName);
                            StringBuilder sb = new StringBuilder();
                            for (String line; (line = br.readLine()) != null; )
                                sb.append(line).append(System.getProperty("line.separator"));
                            asciiImages.add(sb.toString());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        } });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean hasNext() {
        return (index + 1) <= asciiImages.size();
    }

    @Override
    public String getNextImage() {
        return asciiImages.get(++index);
    }

    @Override
    public String getDummyImage() {
        return DUMMY_IMAGE;
    }

    @Override
    public String getCongratsImage() {
        return asciiImages.get(0);
    }
}
