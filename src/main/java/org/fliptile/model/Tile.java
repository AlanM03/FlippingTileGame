package org.fliptile.model;

import javafx.scene.image.Image;
import java.util.Objects;

/**
 * Represents a tile in a memory game. Each tile has an image and can be flipped or matched.
 */
public class Tile {
    private final String imageIdentifier;
    private final Image image;
    private boolean isFlipped;
    private boolean isMatched = false;

    /**
     * Constructs a Tile with a specified image identifier.
     *
     * @param imageIdentifier The unique identifier for the tile's image.
     */
    public Tile(String imageIdentifier) {
        this.imageIdentifier = imageIdentifier;
        this.isFlipped = false;
        this.image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/" + imageIdentifier + ".png")));
    }

    /**
     * Copy constructor for Tile.
     *
     * @param otherTile The tile to copy.
     */
    public Tile(Tile otherTile) {
        this.imageIdentifier = otherTile.imageIdentifier;
        this.isFlipped = otherTile.isFlipped;
        this.image = otherTile.image;
    }

    /**
     * Returns the image of the tile.
     *
     * @return The image associated with the tile.
     */
    public Image getImage() {
        return image;
    }

    /**
     * Flips the tile, changing its state from flipped to not flipped and vice versa.
     */
    public void flip() {
        isFlipped = !isFlipped;
    }

    /**
     * Checks if this tile matches another tile based on the image identifier.
     *
     * @param otherTile The tile to compare with.
     * @return true if the tiles match, false otherwise.
     */
    public boolean matches(Tile otherTile) {
        return this.imageIdentifier.equals(otherTile.imageIdentifier);
    }

    /**
     * Checks if the tile is currently flipped.
     *
     * @return true if the tile is flipped, false otherwise.
     */
    public boolean isFlipped() {
        return isFlipped;
    }

    /**
     * Returns the image identifier of the tile.
     *
     * @return The image identifier.
     */
    public String getImageIdentifier() {
        return imageIdentifier;
    }

    /**
     * Provides a string representation of the tile, including its image identifier and flip state.
     *
     * @return A string representation of the tile.
     */
    @Override
    public String toString() {
        return "Tile{" +
                "imageIdentifier='" + imageIdentifier + '\'' +
                ", isFlipped=" + isFlipped +
                '}';
    }

    /**
     * Checks if the tile is marked as matched.
     *
     * @return true if the tile is matched, false otherwise.
     */
    public boolean isMatched() {
        return isMatched;
    }
}
