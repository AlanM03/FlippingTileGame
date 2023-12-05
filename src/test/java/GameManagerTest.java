import org.fliptile.model.GameManager;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GameManagerTest {

    @Test
    void startGameShouldInitializeGame() {
        GameManager gameManager = new GameManager("Player1");

        gameManager.startGame(4, 4);

        assertTrue(gameManager.isGameInProgress());
        assertNotNull(gameManager.getGameBoard());
        assertNotNull(gameManager.getCurrentPlayer());
        assertEquals(0, gameManager.getMoveCount());
    }

    @Test
    void processMoveShouldFlipTilesAndCheckMatch() {
        GameManager gameManager = new GameManager("Player1");
        gameManager.startGame(4, 4);

        // Choose two valid tiles for a match
        int row1 = 0;
        int col1 = 0;
        int row2 = 0;
        int col2 = 1;

        assertTrue(gameManager.processMove(row1, col1, row2, col2));
        assertEquals(1, gameManager.getMoveCount());
        assertTrue(gameManager.isGameInProgress());
    }

    @Test
    void processInvalidMoveShouldThrowException() {
        GameManager gameManager = new GameManager("Player1");
        gameManager.startGame(4, 4);

        // Choose invalid tiles (out of bounds)
        int row1 = -1;
        int col1 = 0;
        int row2 = 0;
        int col2 = 1;

        assertThrows(IllegalArgumentException.class, () -> gameManager.processMove(row1, col1, row2, col2));
    }

    @Test
    void resetGameShouldStartNewGame() {
        GameManager gameManager = new GameManager("Player1");
        gameManager.startGame(4, 4);

        // Play a move
        gameManager.processMove(0, 0, 0, 1);

        // Reset the game
        gameManager.resetGame();

        assertTrue(gameManager.isGameInProgress());
        assertNotNull(gameManager.getGameBoard());
        assertNotNull(gameManager.getCurrentPlayer());
        assertEquals(0, gameManager.getMoveCount());
    }

    @Test
    void processMatchingTilesShouldEndGameFor6x6() {
        // Test case for a 6x6 board
        GameManager gameManager6x6 = new GameManager("Player2");
        gameManager6x6.startGame(6, 6);

        // Perform moves to match all tiles
        gameManager6x6.processMove(0, 0, 0, 1);
        gameManager6x6.processMove(1, 0, 1, 1);
        gameManager6x6.processMove(2, 0, 2, 1);
        gameManager6x6.processMove(3, 0, 3, 1);
        gameManager6x6.processMove(4, 0, 4, 1);
        gameManager6x6.processMove(5, 0, 5, 1);

        assertEquals(6, gameManager6x6.getMoveCount());
        assertTrue(gameManager6x6.isGameInProgress());
    }
}