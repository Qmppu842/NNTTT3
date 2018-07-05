package fi.qmppu842.nnttt3.Game.profiles;

import fi.qmppu842.nnttt3.Game.GameStateBean;
import static org.testng.Assert.assertTrue;
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
        randProfile.setGameState(gameState);

    }

    @Test
    public void generateNextTurnTest() {
        int[] nextMove = randProfile.getNextTurn();
        gameState.makeMove(nextMove);
        assertTrue(gameState.getBoardSum() == 1);
        assertTrue(gameState.getNext() == -1);
        assertTrue(gameState.getTurnCounter() == 1);
    }

    @Test
    public void generateNextTurnOnFullBoardTest() {
        gameState.setBoard(new int[][]{{1, -1, 1}, {1, -1, 1}, {-1, 1, 0}});
        int[] nextMove = randProfile.getNextTurn();
        gameState.makeMove(nextMove);
        assertTrue(gameState.getBoardSum() == 1);
        assertTrue(gameState.checkAllWinners() == 8);

    }

//    @Test
//    public void fullGameTest() {
//        for (int i = 0; i < 9; i++) {
//            int[] nextMove = randProfile.getNextTurn();
//            gameState.makeMove(nextMove);
//            if (gameState.checkAllWinners() != 8) {
//                break;
//            }
//
//        }
////        assertTrue(gameState.getTurnCounter() == 9);
//
//    }
}
