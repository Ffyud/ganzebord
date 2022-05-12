package nl.traineeship.ganzebord;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardTest {

    Board board = new Board(100);

    @Test
    public void testCurrentTurn() {
        board.setCurrentTurn(5);
        assertEquals(5, board.getCurrentTurn());
    }

    @Test
    public void testDoATurn() {
        board.setCurrentTurn(5);
        assertEquals(6, board.doATurn());
    }

    @Test
    public void testFinish() {
        board.setGameFinished(true);
        assertEquals(true, board.isGameFinished());
    }

    @Test
    public void testBackToStart() {
        boolean isBackToStartPosition = false;

        isBackToStartPosition = board.isBackToStartPosition(25);
        assertEquals(true, isBackToStartPosition);

        isBackToStartPosition = board.isBackToStartPosition(24);
        assertEquals(false, isBackToStartPosition);

        isBackToStartPosition = board.isBackToStartPosition(45);
        assertEquals(true, isBackToStartPosition);

        isBackToStartPosition = board.isBackToStartPosition(46);
        assertEquals(false, isBackToStartPosition);
    }

    @Test
    public void testFinishPosition() {
        boolean isFinished = false;

        isFinished = board.isFinishPosition(63);
        assertEquals(true, isFinished);

        isFinished = board.isFinishPosition(62);
        assertEquals(false, isFinished);

        isFinished = board.isFinishPosition(64);
        assertEquals(false, isFinished);
    }

    @Test
    public void testOverFinishPosition() {
        boolean isOverFinishPosition = false;

        isOverFinishPosition = board.isOverTheFinishPosition(63);
        assertEquals(false, isOverFinishPosition);

        isOverFinishPosition = board.isOverTheFinishPosition(62);
        assertEquals(false, isOverFinishPosition);

        isOverFinishPosition = board.isOverTheFinishPosition(64);
        assertEquals(true, isOverFinishPosition);
    }

    @Test
    public void testInSkipPosition() {
        int skipTurns = 0;

        skipTurns = board.isInSkipTurnPosition(10);
        assertEquals(0, skipTurns);

        skipTurns = board.isInSkipTurnPosition(19);
        assertEquals(1, skipTurns);

        skipTurns = board.isInSkipTurnPosition(52);
        assertEquals(3, skipTurns);
    }

}
