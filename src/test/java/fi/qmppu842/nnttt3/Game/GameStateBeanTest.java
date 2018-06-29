package Game;

import lombok.val;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Qmppu842
 */
public class GameStateBean {

    private GameStateBean gameState;

    public GameStateBean() {
        gameState = new GameStateBean();
    }

    @Before
    public void setUp() {
        gameState.setUpNewGame();
    }

    @Test
    public void setUpTest() {
//       gameState.setUpNewGame();
        int[][] board = gameState.getBoard();
        int sum = 0;
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[x].length; y++) {
                sum += board[x][y];
            }
        }
        assertEquals("Kaikki ruudut ei ole nollassa!", 0, sum);

    }

    @Test
    public void makeMoveTest() {
//        gameState.setUpNewGame();
        int x = 0;
        int y = 0;
        gameState.makeMove(x, y);
        int[][] board = gameState.getBoard();
        assertEquals("Move ei tapahtunut!", 1, board[x][y]);
    }

    @Test
    public void noTurnsChangedTest() {
//        gameState.setUpNewGame();
        int next = gameState.getNext();
        assertEquals("Seuraavaksi vuorossa pitäisi olla 1 mutta oli " + next, 1, next);

    }

    @Test
    public void makeTurnChangeTest() {
//        gameState.setUpNewGame();

        int x = 0;
        int y = 0;
        gameState.makeMove(x, y);
        int next = gameState.getNext();
        assertEquals("makeMove ei tapahtunut!", -1, next);
    }

    @Test
    public void makeTwoTurnChangesTest() {
//        gameState.setUpNewGame();

        int x = 0;
        int y = 0;
        gameState.makeMove(x, y);

        x = 1;
        y = 1;
        gameState.makeMove(x, y);
        int next = gameState.getNext();
        assertEquals("Toka makeMove ei tapahtunut!", 1, next);
    }

    @Test
    public void makeTurnOnSameSquareTest() {
//       gameState.setUpNewGame();

        int x = 0;
        int y = 0;
        gameState.makeMove(x, y);
        gameState.makeMove(x, y);
        int[][] board = gameState.getBoard();
        assertEquals("makeMove Tapahtui samaan ruutuun useammin!", 1, board[x][y]);
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

        assertEquals("In case of draw, board sum should be 1 but was: " + sum, 1, sum);

    }

    private void makeOneMove(int x, int y) {
        gameState.makeMove(x, y);
    }

    private void fillRow(int x) {
        int howMany = gameState.getYSize();
        for (int i = 0; i < howMany; i++) {
            makeOneMove(x, i);
        }
    }

    private void fillColumn(int y) {
        int howMany = gameState.getXSize();
        for (int i = 0; i < howMany; i++) {
            makeOneMove(i, y);
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
            makeOneMove(i, y);
        }
    }

    private int boardSum(int[][] board) {
        int sum = 0;
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[x].length; y++) {
                sum += board[x][y];
            }
        }
        return sum;
    }

    private void setUpNewGame() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private int[][] getBoard() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void makeMove(int x, int y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private int getNext() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private int getXSize() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private int getYSize() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
