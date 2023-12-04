package org.fliptile.model;
public class Player {
    private String name;
    private int score;

    public Player(String name) {
        this.name = name;
        this.score = 0;
    }

    // Copy constructor for creating a new Player object from an existing one
    public Player(Player otherPlayer) {
        this.name = otherPlayer.name;
        this.score = otherPlayer.score;
    }

    public void updateScore(int points) {
        if (points < 0) {
            throw new IllegalArgumentException("Points cannot be negative.");
        }
        this.score += points;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }

    public void resetScore() {
        this.score = 0;
    }
}
