package org.hangman.veiw.Render;

public class ConsoleAsciiRenderer implements Renderer<String>{

    @Override
    public void render(String image) {
        System.out.println(image);
    }
}
