import org.fliptile.model.GameBoard;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameBoardTest {

    @Test
    void constructorShouldInitialize4x4BoardCorrectly() {
        int rows = 4;
        int columns = 4;
        List<String> imageIdentifiers = generateImageIdentifiers(8);

        GameBoard gameBoard = new GameBoard(rows, columns, imageIdentifiers);

        assertEquals(rows, gameBoard.getRows());
        assertEquals(columns, gameBoard.getColumns());
        assertNotNull(gameBoard.getBoard());
        assertEquals(8, gameBoard.getRemainingPairs());
    }

    @Test
    void constructorShouldThrowExceptionForOddDimension() {
        int rows = 3;
        int columns = 3;
        List<String> imageIdentifiers = generateImageIdentifiers(8);

        assertThrows(IllegalArgumentException.class, () -> new GameBoard(rows, columns, imageIdentifiers));
    }

    @Test
    void constructorShouldInitialize6x6BoardCorrectly() {
        int rows = 6;
        int columns = 6;
        List<String> imageIdentifiers = generateImageIdentifiers(18);

        GameBoard gameBoard = new GameBoard(rows, columns, imageIdentifiers);

        assertEquals(rows, gameBoard.getRows());
        assertEquals(columns, gameBoard.getColumns());
        assertNotNull(gameBoard.getBoard());
        assertEquals(18, gameBoard.getRemainingPairs());
    }

    @Test
    void constructorShouldInitialize8x8BoardCorrectly() {
        int rows = 8;
        int columns = 8;
        List<String> imageIdentifiers = generateImageIdentifiers(32);

        GameBoard gameBoard = new GameBoard(rows, columns, imageIdentifiers);

        assertEquals(rows, gameBoard.getRows());
        assertEquals(columns, gameBoard.getColumns());
        assertNotNull(gameBoard.getBoard());
        assertEquals(32, gameBoard.getRemainingPairs());
    }

    private List<String> generateImageIdentifiers(int numberOfImages) {
        List<String> identifiers = new ArrayList<>();
        for (int i = 1; i <= numberOfImages; i++) {
            identifiers.add("image" + i);
        }
        return identifiers;
    }
}