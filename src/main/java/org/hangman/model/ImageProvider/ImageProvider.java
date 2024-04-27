package org.hangman.model.ImageProvider;

public interface ImageProvider<T> {
    public boolean hasNext();
    public T getNextImage();
    public T getDummyImage();
    public T getCongratsImage();
}
