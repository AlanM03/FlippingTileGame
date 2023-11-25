package org.fliptile.model;
public class Tile {
    private final String imageIdentifier;
    private boolean isFlipped;

    public Tile(String imageIdentifier) {
        this.imageIdentifier = imageIdentifier;
        this.isFlipped = false;
    }

    public Tile(Tile otherTile) {
        this.imageIdentifier = otherTile.imageIdentifier;
        this.isFlipped = otherTile.isFlipped;
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
