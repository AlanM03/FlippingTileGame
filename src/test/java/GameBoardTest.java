import org.fliptile.GameBoard;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GameBoardTest {

    @Test
    void constructorShouldInitializeBoardCorrectly() {
        int rows = 4;
        int columns = 4;
        GameBoard gameBoard = new GameBoard(rows, columns);

        assertEquals(rows, gameBoard.getRows());
        assertEquals(columns, gameBoard.getColumns());
        assertNotNull(gameBoard.getBoard());
        assertEquals(8, gameBoard.getRemainingPairs());
    }

    @Test
    void constructorShouldThrowExceptionForOddDimension() {
        int rows = 3;
        int columns = 3;

        assertThrowsExactly(IllegalArgumentException.class, () -> new GameBoard(rows, columns));
    }
}
