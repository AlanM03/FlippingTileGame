package org.fliptile.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TileTest {

    @Test
    public void testConstructor() {
        Tile tile = new Tile("image1");
        assertEquals("image1", tile.getImageIdentifier());
        assertFalse(tile.isFlipped());
        assertFalse(tile.isMatched());
    }

    @Test
    public void testFlip() {
        Tile tile = new Tile("image3");

        assertFalse(tile.isFlipped());
        tile.flip();
        assertTrue(tile.isFlipped());
        tile.flip();
        assertFalse(tile.isFlipped());
    }

    @Test
    public void testMatches() {
        Tile tile1 = new Tile("image4");
        Tile tile2 = new Tile("image4");
        Tile tile3 = new Tile("image5");

        assertTrue(tile1.matches(tile2));
        assertFalse(tile1.matches(tile3));
    }

    @Test
    public void testToString() {
        Tile tile = new Tile("image6");
        tile.flip();

        String expectedString = "Tile{imageIdentifier='image6', isFlipped=true}";
        assertEquals(expectedString, tile.toString());
    }


    @Test
    public void testGetImage() {
        Tile tile = new Tile("image8");
        assertNotNull(tile.getImage());
    }
}