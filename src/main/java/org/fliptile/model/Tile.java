package org.fliptile.model;

import javafx.scene.image.Image;

import java.util.Objects;

public class Tile {
    private final String imageIdentifier;
    private final Image image;
    private boolean isFlipped;

    public Tile(String imageIdentifier) {
        this.imageIdentifier = imageIdentifier;
        this.isFlipped = false;
        this.image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/" + imageIdentifier + ".png")));
    }

    public Tile(Tile otherTile, Image image) {
        this.imageIdentifier = otherTile.imageIdentifier;
        this.isFlipped = otherTile.isFlipped;
        this.image = otherTile.image;
    }

    public Image getImage() {
        return image;
    }

    public void flip() {
        isFlipped = !isFlipped;
    }

    public boolean matches(Tile otherTile) {
        return this.imageIdentifier.equals(otherTile.imageIdentifier);
    }

    public boolean isFlipped() {
        return isFlipped;
    }

    public String getImageIdentifier() {
        return imageIdentifier;
    }

    @Override
    public String toString() {
        return "Tile{" +
                "imageIdentifier='" + imageIdentifier + '\'' +
                ", isFlipped=" + isFlipped +
                '}';
    }
}
