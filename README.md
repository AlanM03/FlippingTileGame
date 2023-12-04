# Flipping Tile Memory Game

## Introduction
This project is a tile-flipping card game developed using Java with JavaFX. It's a fun and engaging game where players flip tiles to reveal playing cards, aiming to match pairs. The game comes with three different board sizes: 4x4, 6x6, and 8x8, catering to various difficulty levels. Players can keep track of their scores, making it perfect for both casual gaming and challenging play.

## Project Structure
```plaintext
FlippingTileProject/
├───main/
│   ├───java/
│   │   │   module-info.java
│   │   │
│   │   └───org/
│   │       └───fliptile/
│   │           ├───controller/ - Handles interaction between model classes and FXML
│   │           │       GameViewController.java - Handles game view
│   │           │       MainMenuController.java - Handles main menu
│   │           │       VictoryViewController.java - Handles game victory
│   │           │
│   │           ├───model/ Business logic
│   │           │       GameBoard.java - Logic for game board
│   │           │       GameManager.java - Logic to manage game classes
│   │           │       Player.java - Logic regarding player
│   │           │       Tile.java - Logic for tiles
│   │           │
│   │           └───view/
│   │                   Main.java - Main point of program
│   │                   
│   │
│   └───resources/
│       │   GameView.fxml -- Corresponds to GameViewController.java, loads context in FXML
│       │   MainMenu.fxml -- Corresponds to MainMenuController.java, loads context in FXML
│       │   Victory.fxml -- Corresponds to VictoryViewController.java, loads context in FXML
│       │
│       ├───images/ -- Assets for symbols in GameView
└───test/ -- Test classes with JUnit 5
    ├───java/
    │       GameBoardTest.java
    │  
    └───resources/
```

## Installation

### Prerequisites
- Java JDK 17
- Gradle

### Steps
1. Clone the repository: `git clone https://github.com/ddamme05/FlippingTile.git`
2. Navigate to the project directory: `cd FlippingTileProject`
3. Build the project using Gradle: `./gradlew build`

## Usage
To play the game, follow these steps:

1. Start the game by running `Main.java`.
2. Choose the board size (4x4, 6x6, 8x8) from the main menu.
3. Start flipping tiles to find matching pairs of cards.
4. Keep track of your score and try to complete the game with the fewest flips possible.

## Features
- **Multiple Board Sizes:** Choose from 4x4, 6x6, and 8x8 boards.
- **Score Tracking:** Keep track of your score based on the number of flips.
- **Intuitive UI:** User-friendly interface for an engaging gaming experience.
- **Replayability:** Randomized card placements for a new challenge every game.

---
