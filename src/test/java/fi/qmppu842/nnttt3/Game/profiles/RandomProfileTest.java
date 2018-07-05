package fi.qmppu842.nnttt3.Game.profiles;

import fi.qmppu842.nnttt3.Game.GameStateBean;
import java.awt.Point;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Qmppu842
 */
public class RandomProfileTest {

    private RandomProfile randProfile;
    private GameStateBean gameState;

    @BeforeMethod
    public void setUp() {
        randProfile = new RandomProfile();
        gameState = new GameStateBean();
        gameState.setUpNewGame();
        randProfile.setGameState(gameState);

    }

    @Test
    public void generateNextTurnTest() {
        Point nextMove = randProfile.getNextTurn();
        gameState.makeMove(nextMove);
        assertEquals(gameState.getBoardSum(), 1);
        assertEquals(gameState.getNext(), -1);
        assertEquals(gameState.getTurnCounter(), 1);
    }

    @Test
    public void generateNextTurnOnFullBoardTest() {
        gameState.setBoard(new int[][]{{1, -1, 1}, {1, -1, 1}, {-1, 1, 0}});
        gameState.setNext(-1);
        Point nextMove = randProfile.getNextTurn();
        gameState.makeMove(nextMove);
        int boardSum = gameState.getBoardSum();
        assertEquals(boardSum, 1);
        System.out.println("moiiii");
        int winner = gameState.checkAllWinners();
        assertEquals(winner, 8);

    }

}
