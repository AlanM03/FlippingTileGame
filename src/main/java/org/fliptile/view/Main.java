package org.fliptile.view;

import org.fliptile.model.GameManager;

public class Main {
    public static void main(String[] args) {
        GameManager gameManager = new GameManager("PlayerName");
        ConsoleInterface consoleInterface = new ConsoleInterface(gameManager);
        consoleInterface.start();
    }
}
