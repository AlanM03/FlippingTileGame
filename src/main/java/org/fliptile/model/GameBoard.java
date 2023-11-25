package org.fliptile.model;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public class GameBoard {
    private final Tile[][] board;
    private final int rows;
    private final int columns;
    private int remainingPairs;
    private boolean isGameOver;

    public GameBoard(int rows, int columns) {
        if (rows * columns % 2 != 0) {
            throw new IllegalArgumentException("The number of tiles must be even.");
        }

        this.rows = rows;
        this.columns = columns;
        this.board = new Tile[rows][columns];
        this.remainingPairs = (rows * columns) / 2;
        this.isGameOver = false;
        initializeBoard();
    }

    // Initialize the board with pairs of matching tiles
    private void initializeBoard() {
        List<Tile> tiles = new ArrayList<>();
        int pairId = 1;

        // Create pairs of tiles
        for (int i = 0; i < remainingPairs; i++) {
            Tile tile1 = new Tile("Pair " + pairId);
            Tile tile2 = new Tile("Pair " + pairId);
            tiles.add(tile1);
            tiles.add(tile2);
            pairId++;
        }

        // Shuffle the tiles, we are using Fisher-Yates here and it's O(n), so pretty good
        Collections.shuffle(tiles);

        // Place the tiles on the board
        int tileIndex = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                board[row][col] = tiles.get(tileIndex);
                tileIndex++;
            }
        }
    }

    // Display the current state of the board
    public void displayBoard() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                Tile tile = board[row][col];
                System.out.print(tile.isFlipped() ? tile.getImageIdentifier() + "\t" : "X\t");
            }
            System.out.println();
        }
    }

    // Check if two selected tiles are a match
    public boolean checkMatch(Tile firstTile, Tile secondTile) {
        if (firstTile.matches(secondTile)) {
            remainingPairs--;
            return true;
        }
        return false;
    }

    public void updateBoard() {
        if (remainingPairs == 0) {
            isGameOver = true;
        }
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public int getRows(){
        return rows;
    }

    public int getColumns(){
        return columns;
    }
    public Tile[][] getBoard() {
        return board;
    }

    public int getRemainingPairs() {
        return remainingPairs;
    }
}