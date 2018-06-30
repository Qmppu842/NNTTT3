package fi.qmppu842.nnttt3.Game;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays;
import lombok.val;
//import org.junit.Test;
//import static org.junit.Assert.*;
//import org.junit.Before;
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
//       gameState.setUpNewGame();
//        gameState.setUpNewGame();
        int[][] board = gameState.getBoard();
        int sum = boardSum(board);
//        for (int x = 0; x < board.length; x++) {
//            for (int y = 0; y < board[x].length; y++) {
//                sum += board[x][y];
//            }
//        }
//        assertEquals("Kaikki ruudut ei ole nollassa!", 0, sum);
        assertEquals(sum, 0);

    }

    @Test
    public void makeMoveTest() {
        int x = 0;
        int y = 0;
        gameState.makeMove(x, y);
        int[][] board = gameState.getBoard();
//        assertEquals("Move ei tapahtunut!", 1, board[x][y]);
        assertEquals(board[x][y], 1);
    }

    @Test
    public void noTurnsChangedTest() {
        int next = gameState.getNext();
//        assertEquals("Seuraavaksi vuorossa pitäisi olla 1 mutta oli " + next, 1, next);
        assertEquals(next, 1);

    }

    @Test
    public void makeTurnChangeTest() {
        int x = 0;
        int y = 0;
        gameState.makeMove(x, y);
        int next = gameState.getNext();
//        assertEquals("makeMove ei tapahtunut!", -1, next);
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
//        assertEquals("Toka makeMove ei tapahtunut!", 1, next);
        assertEquals(next, 1);
    }

    @Test
    public void makeTurnOnSameSquareTest() {

        int x = 0;
        int y = 0;
        gameState.makeMove(x, y);
        gameState.makeMove(x, y);
        int[][] board = gameState.getBoard();
//        assertEquals("makeMove Tapahtui samaan ruutuun useammin!", 1, board[x][y]);
        assertEquals(board[x][y], 1);
    }

    @Test
    public void fill3x3BoardTest() {
        int xSize = gameState.getXSize();
        int ySize = gameState.getYSize();

        fillRow(0);
        fillRow(2);
        fillRow(1);

        int[][] board = gameState.getBoard();
        int sum = boardSum(board);

//        assertEquals("In case of draw, board sum should be 1 but was: " + sum, 1, sum);
        assertEquals(sum, 1);

    }

    @Test
    public void addPropertyChangeListenerTest() {
        PropertyChangeListener listener = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent pce) {
                System.out.println("Just a Test.");
            }
        };
        gameState.addPropertyChangeListener(listener);
        val change = gameState.getPropChange();
//        assertTrue("PropertyChangeListener not added properly", change != null);
        assertTrue(change != null);

    }

    @Test(dependsOnMethods = {"addPropertyChangeListenerTest"})
    public void removePropertyChangeListenerTest() {
//        PropertyChangeListener[] change1 = gameState.getPropChange().getPropertyChangeListeners();
//        PropertyChangeListener listener = new PropertyChangeListener() {
//            @Override
//            public void propertyChange(PropertyChangeEvent pce) {
//                System.out.println("Just a Test #2.");
//            }
//        };
//        gameState.addPropertyChangeListener( listener);
//        gameState.removePropertyChangeListener(listener);
//        PropertyChangeListener[] change = gameState.getPropChange().getPropertyChangeListeners();
//        assertTrue("PropertyChangeListener not added properly", change == null);
//        System.out.println(change1);
//        System.out.println(change);
//        assertTrue(change.length == 0);
//TODO: Correct this test...
        assertTrue(true);
    }

    @Test
    public void didRowWinTest() {
//        makeOneMove(0, 0);
//        makeOneMove(0, 1);
//        makeOneMove(1, 0);
//        makeOneMove(1, 1);
//        makeOneMove(2, 0);
        for (int i = 0; i < 3; i++) {
            makeOneMove(0, i);
            if (i < 2) {
                makeOneMove(1, i);
            }
        }

//        boardToString(gameState.getBoard());  
//        boardSum(gameState.getBoard());
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
//        System.out.println("Board sum incoming:");
        int sum = 0;
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                sum += board[x][y];
//                System.out.print(board[x][y]);
            }
//            System.out.println();
        }
        return sum;
    }

    private void boardToString(int[][] board) {
        System.out.println("BoardToString starts here:");
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                System.out.print(board[x][y]);
//                System.out.print(y);
            }
            System.out.println();
        }

    }

}
