package fi.qmppu842.nnttt3.Game;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Arrays;
import org.testng.annotations.Test;
import static org.testng.Assert.*;
import org.testng.annotations.*;

/**
 *
 * @author Qmppu842
 */
public class GameStateBeanTest {

    private GameStateBean gameState;

    public GameStateBeanTest() {
        gameState = new GameStateBean();
    }

    @BeforeMethod
    public void setUp() {
        gameState.setUpNewGame();
    }

    @Test
    public void setUpTest() {
        int[][] board = gameState.getBoard();
        int sum = boardSum(board);
        assertEquals(sum, 0);

    }

    @Test
    public void makeMoveTest() {
        int x = 0;
        int y = 0;
        gameState.makeMove(x, y);
        int[][] board = gameState.getBoard();
        assertEquals(board[x][y], 1);
    }

    @Test
    public void noTurnsChangedTest() {
        int next = gameState.getNext();
        assertEquals(next, 1);

    }

    @Test
    public void makeTurnChangeTest() {
        int x = 0;
        int y = 0;
        gameState.makeMove(x, y);
        int next = gameState.getNext();
        assertEquals(next, -1);
    }

    @Test
    public void makeTwoTurnChangesTest() {
        int x = 0;
        int y = 0;
        gameState.makeMove(x, y);

        x = 1;
        y = 1;
        gameState.makeMove(x, y);
        int next = gameState.getNext();
        assertEquals(next, 1);
    }

    @Test
    public void makeTurnOnSameSquareTest() {

        int x = 0;
        int y = 0;
        gameState.makeMove(x, y);
        gameState.makeMove(x, y);
        int[][] board = gameState.getBoard();
        assertEquals(board[x][y], 1);
    }

    @Test
    public void fill3x3BoardTest() {
        int xSize = gameState.getXSize();
        int ySize = gameState.getYSize();

        fillTie();
        int[][] board = gameState.getBoard();
        int sum = boardSum(board);

        assertEquals(sum, 1);

    }

    @Test
    public void addPropertyChangeListenerTest() {
        PropertyChangeListener listener = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent pce) {
//                System.out.println("Just a Test.");
            }
        };
        gameState.addPropertyChangeListener(listener);
        PropertyChangeSupport change = gameState.getPropChange();
        assertTrue(change != null);

    }

    @Test(dependsOnMethods = {"addPropertyChangeListenerTest"})
    public void removePropertyChangeListenerTest() {
//      TODO: Make actual test for this...
        assertTrue(true);
    }

    @Test
    public void boardSumTest() {
        assertTrue(gameState.getBoardSum() == 0);
        makeOneMove(0, 0);
        assertTrue(gameState.getBoardSum() == 1);
        makeOneMove(0, 1);
        assertTrue(gameState.getBoardSum() == 0);
        gameState.setUpNewGame();
        fillTie();
        assertTrue(gameState.getBoardSum() == 1);

    }

    @Test
    public void horizontalWinTest() {
//        TODO: Reflection API seems dang cool.
        gameState.setBoard(new int[][]{{1, 1, 1}, {0, 0, 0}, {0, 0, 0}});
        assertTrue(gameState.checkHorWinner() == 3);
        gameState.setBoard(new int[][]{{0, 0, 0}, {1, 1, 1}, {0, 0, 0}});
        assertTrue(gameState.checkHorWinner() == 3);
        gameState.setBoard(new int[][]{{0, 0, 0}, {0, 0, 0}, {1, 1, 1}});
        assertTrue(gameState.checkHorWinner() == 3);
    }

    @Test
    public void verticalWinTest() {
        gameState.setBoard(new int[][]{{1, 0, 0}, {1, 0, 0}, {1, 0, 0}});
        assertTrue(gameState.checkVerWinner() == 3);
        gameState.setBoard(new int[][]{{0, 1, 0}, {0, 1, 0}, {0, 1, 0}});
        assertTrue(gameState.checkVerWinner() == 3);
        gameState.setBoard(new int[][]{{0, 0, 1}, {0, 0, 1}, {0, 0, 1}});
        assertTrue(gameState.checkVerWinner() == 3);

    }

    @Test
    public void diagonalWinTest() {
        gameState.setBoard(new int[][]{{1, 0, 0}, {0, 1, 0}, {0, 0, 1}});
        assertTrue(gameState.checkDiagWinner() == 3);
        gameState.setBoard(new int[][]{{0, 0, 1}, {0, 1, 0}, {1, 0, 0}});
        assertTrue(gameState.checkDiagWinner() == 3);
        gameState.setBoard(new int[][]{{-1, 0, 0}, {0, -1, 0}, {0, 0, -1}});
        assertTrue(gameState.checkDiagWinner() == -3);
        gameState.setBoard(new int[][]{{0, 0, -1}, {0, -1, 0}, {-1, 0, 0}});
        assertTrue(gameState.checkDiagWinner() == -3);
    }

    @Test
    public void nextCorrectValueTest() {
        makeOneMove(0, 0);
        int next = gameState.getNext();
        assertTrue(next == -1);
        makeOneMove(0, 0);
        next = gameState.getNext();
        assertTrue(next == -1);
        makeOneMove(0, 1);
        next = gameState.getNext();
        assertTrue(next == 1);
        assertTrue(gameState.getTurnCounter() == 2);
        
    }

    private void makeOneMove(int y, int x) {
        gameState.makeMove(y, x);
    }

    private void fillRow(int x) {
        int howMany = gameState.getYSize();
        for (int i = 0; i < howMany; i++) {
            makeOneMove(i, x);
        }
    }

    private void fillColumn(int y) {
        int howMany = gameState.getXSize();
        for (int i = 0; i < howMany; i++) {
            makeOneMove(y, i);
        }
    }

    private void fillTie() {
        fillRow(0);
        fillRow(2);
        fillRow(1);
    }

    private void playXVictory() {
        for (int i = 0; i < 3; i++) {
            makeOneMove(0, i);
            if (i < 2) {
                makeOneMove(1, i);
            }
        }
    }

    private void makeManyMovesInRow(int x, int howMany) {
        howMany = Math.min(gameState.getYSize(), howMany);
        for (int i = 0; i < howMany; i++) {
            makeOneMove(x, i);
        }
    }

    private void makeManyMovesInColumn(int y, int howMany) {
        howMany = Math.min(gameState.getYSize(), howMany);
        for (int i = 0; i < howMany; i++) {
            makeOneMove(y, i);
        }
    }

    private int boardSum(int[][] board) {
        int sum = 0;
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                sum += board[x][y];
            }
        }
        return sum;
    }

    private void boardToString(int[][] board, String kukaTulostaa) {
        System.out.println("Nyt tulostus vuorossa on: " + kukaTulostaa);
        System.out.println("BoardToString starts here:");
        System.out.println(Arrays.deepToString(board));

    }

}
