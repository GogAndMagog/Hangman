package org.hangman.model.ImageProvider;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
        List<String> filesPaths = new ArrayList<>();
        StringBuilder sb;

        InputStream is = AsciiImageProvider.class.getClassLoader().getResourceAsStream(resourcePath);
        if (is == null)
            return;

        InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(streamReader);
        try {
            for (String line; (line = reader.readLine()) != null; )
                filesPaths.add(resourcePath + "/" + line);
            for (String filePath : filesPaths) {
                is = AsciiImageProvider.class.getClassLoader().getResourceAsStream(filePath);
                if (is == null)
                    continue;

                streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
                reader = new BufferedReader(streamReader);

                sb = new StringBuilder();
                for (String line; (line = reader.readLine()) != null; )
                    sb.append(line).append(System.getProperty("line.separator"));

                asciiImages.add(sb.toString());
            }
        } catch (Exception e) {
        }

        if (asciiImages.size() > 2)
            index = 1;
    }

    @Override
    public boolean hasNext() {
        return (index + 1) <= asciiImages.size();
    }

    @Override
    public String getNextImage() {
        return asciiImages.get(index++);
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
