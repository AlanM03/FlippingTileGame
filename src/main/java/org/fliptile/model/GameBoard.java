package org.fliptile.model;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public class GameBoard {
    private final Tile[][] board;
    private final int rows;
    private final int columns;
    private int remainingPairs;


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

    public boolean checkMatch(Tile firstTile, Tile secondTile) {
        if (firstTile.matches(secondTile)) {
            remainingPairs--;
            return true;
        }
        return false;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public Tile[][] getBoard() {
        return board;
    }

    public int getRemainingPairs() {
        return remainingPairs;
    }

    public int getTotalPairs() {
        return (rows * columns) / 2;
    }
}
