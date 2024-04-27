package org.hangman.model.DomainObjects;

import org.hangman.model.ImageProvider.ImageProvider;

import java.util.SplittableRandom;

public class HangedMan<ImageType> {
    private HangedManState state;
    private ImageType image;
    private ImageProvider<ImageType> imageProvider;
    private int healthPoints;

    public HangedMan(ImageProvider<ImageType> imageProvider, int healthPoints) {
        this.imageProvider = imageProvider;
        this.state = HangedManState.ALIVE;
        this.healthPoints = healthPoints;
    }

    public ImageType getImage() {
        return image;
    }

    public void doWorse() {
        healthPoints--;
        if (imageProvider.hasNext())
            image = imageProvider.getNextImage();
        else
            image = imageProvider.getDummyImage();

        if (healthPoints <= 0)
            state = HangedManState.DEAD;
    }

    public void release() {
        state = HangedManState.RELEASED;
        image = imageProvider.getCongratsImage();
    }

    public boolean isAlive() {
        return state == HangedManState.ALIVE;
    }

}
