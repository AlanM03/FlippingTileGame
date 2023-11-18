package org.fliptile;

public class Main {
    public static void main(String[] args) {
        GameManager gameManager = new GameManager("PlayerName");
        ConsoleInterface consoleInterface = new ConsoleInterface(gameManager);
        consoleInterface.start();
    }
}
