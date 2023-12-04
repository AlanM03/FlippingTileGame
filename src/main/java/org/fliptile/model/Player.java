package org.fliptile.model;

/**
 * Represents a player in a memory game. Each player has a name and a score.
 */
public class Player {
    private String name;
    private int score;

    /**
     * Constructs a new Player with a specified name.
     *
     * @param name The name of the player.
     */
    public Player(String name) {
        this.name = name;
        this.score = 0;
    }

    /**
     * Copy constructor for creating a new Player object from an existing one.
     *
     * @param otherPlayer The player to be copied.
     */
    public Player(Player otherPlayer) {
        this.name = otherPlayer.name;
        this.score = otherPlayer.score;
    }

    /**
     * Updates the player's score by a specified number of points.
     *
     * @param points The number of points to add to the player's score.
     * @throws IllegalArgumentException if the number of points is negative.
     */
    public void updateScore(int points) {
        if (points < 0) {
            throw new IllegalArgumentException("Points cannot be negative.");
        }
        this.score += points;
    }

    /**
     * Returns the name of the player.
     *
     * @return The name of the player.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets or updates the name of the player.
     *
     * @param name The new name of the player.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the current score of the player.
     *
     * @return The score of the player.
     */
    public int getScore() {
        return score;
    }

    /**
     * Provides a string representation of the player, including their name and current score.
     *
     * @return A string representation of the player.
     */
    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }

    /**
     * Resets the player's score to zero.
     */
    public void resetScore() {
        this.score = 0;
    }
}
