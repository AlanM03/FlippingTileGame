package org.fliptile;
import java.util.Scanner;

public class ConsoleInterface {
    private final GameManager gameManager;
    private final Scanner scanner;

    public ConsoleInterface(GameManager gameManager) {
        this.gameManager = gameManager;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean exit = false;
        while (!exit) {
            displayMenu();
            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        setupAndStartGame();
                        break;
                    case 2:
                        showInstructions();
                        break;
                    case 3:
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                if(scanner.hasNext()){
                    scanner.nextLine();
                } else {
                    exit = true;
                }
            }
        }
    }

    private void displayMenu() {
        System.out.println("\n--- Memory Game Menu ---");
        System.out.println("1. Start New Game");
        System.out.println("2. Show Instructions");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
    }

    private void setupAndStartGame() {
        System.out.println("Select board size: 4x4, 6x6, or 8x8. Enter 4, 6, or 8:");
        if (scanner.hasNextInt()) {
            int size = scanner.nextInt();
            if (size != 4 && size != 6 && size != 8) {
                System.out.println("Invalid board size. Please select 4, 6, or 8.");
                return;
            }
            gameManager.startGame(size, size);
            playGame();
        } else {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine();
            return;
        }
    }

    private void playGame() {
        while (gameManager.isGameInProgress()) {
            System.out.println("Enter the row and column indices of the first tile (e.g., 0 1), or -1 to exit:");
            if (scanner.hasNextInt()) {
                int row1 = scanner.nextInt();
                if (row1 == -1) {
                    System.out.println("Exiting game...");
                    return;
                }
                if (!scanner.hasNextInt()) {
                    System.out.println("Invalid input. Please enter numbers.");
                    scanner.nextLine();
                    continue;
                }
                int col1 = scanner.nextInt();

                System.out.println("Enter the row and column indices of the second tile (e.g., 1 2):");
                if (!scanner.hasNextInt()) {
                    System.out.println("Invalid input. Please enter numbers.");
                    scanner.nextLine();
                    continue;
                }
                int row2 = scanner.nextInt();
                if (!scanner.hasNextInt()) {
                    System.out.println("Invalid input. Please enter numbers.");
                    scanner.nextLine();
                    continue;
                }
                int col2 = scanner.nextInt();

                gameManager.processMove(row1, col1, row2, col2);

                if (!gameManager.isGameInProgress()) {
                    System.out.println("Game Over. Your score: " + gameManager.getCurrentPlayer().getScore());
                    break;
                }
            } else {
                System.out.println("Invalid input. Please enter numbers.");
                scanner.nextLine();
                continue;
            }
        }
    }

    private void showInstructions() {
        System.out.println("Memory Game Instructions:");
        System.out.println("1. A number of tiles are laid face down on a board.");
        System.out.println("2. Two tiles are flipped face up over each turn.");
        System.out.println("3. The objective of the game is to turn over pairs of matching tiles.");
        System.out.println("4. The game ends when all pairs are matched.");
    }
}
