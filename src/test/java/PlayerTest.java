package org.fliptile.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    @Test
    public void testConstructor() {
        Player player = new Player("John");
        assertEquals("John", player.getName());
        assertEquals(0, player.getScore());
    }

    @Test
    public void testCopyConstructor() {
        Player originalPlayer = new Player("Alice");
        originalPlayer.updateScore(42);

        Player copiedPlayer = new Player(originalPlayer);
        assertEquals("Alice", copiedPlayer.getName());
        assertEquals(42, copiedPlayer.getScore());
    }

    @Test
    public void testUpdateScore() {
        Player player = new Player("Bob");

        // Positive points
        player.updateScore(10);
        assertEquals(10, player.getScore());

        // Negative points should throw an exception
        assertThrows(IllegalArgumentException.class, () -> player.updateScore(-5));
        assertEquals(10, player.getScore()); // Score should remain unchanged
    }

    @Test
    public void testSetName() {
        Player player = new Player("Charlie");
        assertEquals("Charlie", player.getName());

        player.setName("David");
        assertEquals("David", player.getName());
    }

    @Test
    public void testToString() {
        Player player = new Player("Eve");
        player.updateScore(20);

        String expectedString = "Player{name='Eve', score=20}";
        assertEquals(expectedString, player.toString());
    }

    @Test
    public void testResetScore() {
        Player player = new Player("Frank");
        player.updateScore(30);

        assertEquals(30, player.getScore());

        player.resetScore();
        assertEquals(0, player.getScore());
    }
}