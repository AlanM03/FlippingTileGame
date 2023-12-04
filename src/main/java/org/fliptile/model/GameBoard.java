package org.fliptile.model;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the game board in a memory game, consisting of a grid of tiles.
 */
public class GameBoard {
    private final Tile[][] board;
    private final int rows;
    private final int columns;
    private int remainingPairs;

    /**
     * Constructs a GameBoard with specified dimensions and a list of image identifiers.
     *
     * @param rows The number of rows in the game board.
     * @param columns The number of columns in the game board.
     * @param imageIdentifiers A list of image identifiers for creating tile pairs.
     * @throws IllegalArgumentException if the total number of tiles (rows * columns) is not even.
     */
    public GameBoard(int rows, int columns, List<String> imageIdentifiers) {
        if (rows * columns % 2 != 0) {
            throw new IllegalArgumentException("The number of tiles must be even.");
        }

        this.rows = rows;
        this.columns = columns;
        this.board = new Tile[rows][columns];
        this.remainingPairs = (rows * columns) / 2;
        initializeBoard(imageIdentifiers);
    }

    /**
     * Initializes the board with shuffled tiles based on the provided image identifiers.
     *
     * @param imageIdentifiers A list of image identifiers for creating tile pairs.
     */
    private void initializeBoard(List<String> imageIdentifiers) {
        List<Tile> tiles = new ArrayList<>();
        for (String identifier : imageIdentifiers) {
            Tile tile1 = new Tile(identifier);
            Tile tile2 = new Tile(identifier);
            tiles.add(tile1);
            tiles.add(tile2);
        }
        Collections.shuffle(tiles);

        int tileIndex = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                board[row][col] = tiles.get(tileIndex++);
            }
        }
    }

    /**
     * Checks if two tiles are a match.
     *
     * @param firstTile The first tile to be checked.
     * @param secondTile The second tile to be checked.
     * @return True if the tiles match, false otherwise.
     */
    public boolean checkMatch(Tile firstTile, Tile secondTile) {
        if (firstTile.matches(secondTile)) {
            remainingPairs--;
            return true;
        }
        return false;
    }

    /**
     * Returns the number of rows in the game board.
     *
     * @return The number of rows.
     */
    public int getRows() {
        return rows;
    }

    /**
     * Returns the number of columns in the game board.
     *
     * @return The number of columns.
     */
    public int getColumns() {
        return columns;
    }

    /**
     * Returns the game board as a 2D array of Tiles.
     *
     * @return The 2D array of Tiles.
     */
    public Tile[][] getBoard() {
        return board;
    }

    /**
     * Returns the number of remaining pairs that need to be matched.
     *
     * @return The number of remaining pairs.
     */
    public int getRemainingPairs() {
        return remainingPairs;
    }

    /**
     * Returns the total number of pairs on the game board.
     *
     * @return The total number of pairs.
     */
    public int getTotalPairs() {
        return (rows * columns) / 2;
    }
}
