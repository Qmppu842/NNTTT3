package fi.qmppu842.nnttt3.Game;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Arrays;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Qmppu842
 */
@Data
public class GameStateBean {

    @Getter
    private int[][] board;
    @Getter
    private int next;
    private PropertyChangeSupport propChange;
//    TODO: delombok things to get proper javadoc
//    Currently -1 means O wins, 0 draw, 1 X wins and 2 game is still going
    @Getter
    private int winner;
    private int turnCounter;

    public GameStateBean() {
        propChange = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propChange.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propChange.removePropertyChangeListener(listener);
    }

    public void setUpNewGame() {
//        System.out.println("Setup");
        board = new int[3][3];
//        System.out.println(Arrays.deepToString(board));
        next = 1;
        winner = 8;
        turnCounter = 0;
    }

    public void setUpNewGameCustom() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void makeMove(int y, int x) {
        if (winner != 8) {
            return;
        }
        int oldBoard = board[y][x];
        if (oldBoard == 0) {
            board[y][x] = next;
            next *= -1;
            turnCounter++;
        }
        propChange.firePropertyChange("makeMove", oldBoard, board[y][x]);

    }

    public final int getXSize() {
        return board.length;
    }

    public final int getYSize() {
        return board[0].length;
    }

    public int getBoardSum() {
        int sum = 0;
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                sum += board[x][y];
            }
        }
        return sum;
    }

    /**
     *
     * @return -3 = O wins,<br> +3 X wins,
     * <br> 8 Tie,
     * <br> any other = game is still going
     */
    public int checkAllWinners() {
        int sum = 0;
//    sum = checkHorWinner();
//        if (Math.abs(sum) >) {
//            
//        }
//    How wants clarity or clear code if newer versions allow you to write uglier code than ever
//    My apologies to anyone who wanted no variables defined on start but I was not able to be that evil...
        int hor = checkHorWinner();
        int ver = checkVerWinner();
        int diag = checkDiagWinner();
        sum = (Math.abs(hor) == 3 ? hor
                : (Math.abs(ver) == 3 ? ver
                : (Math.abs(diag) == 3 ? diag
                : (diag == 8 ? diag : 0))));
        return sum;
    }

    public int checkHorWinner() {
        int sum = 0;
        for (int y = 0; y < board.length; y++) {
            sum = (Math.abs(sum) >= 3 ? sum : 0);
            for (int x = 0; x < board[y].length; x++) {
                sum += board[x][y];
            }
        }
//        if (turnCounter < 9) {
//            sum = 2;
//        }
//        if (turnCounter >= 9 && Math.abs(sum) <= 2) {
//            sum = 8;
//        }
        return sum;
    }

    public int checkVerWinner() {
        int sum = 0;
        for (int x = 0; x < board.length; x++) {
            sum = (Math.abs(sum) >= 3 ? sum : 0);
            for (int y = 0; y < board[x].length; y++) {
                sum += board[x][y];
            }
        }
//        if (turnCounter < 9) {
//            sum = 2;
//        }
//        if (turnCounter >= 9 && Math.abs(sum) <= 2) {
//            sum = 8;
//        }
        return sum;
    }

    /**
     *
     * @return -3 = O wins,<br> +3 X wins,
     * <br> 8 Tie,
     * <br> any other = game is still going
     */
    public int checkDiagWinner() {
        int sum = 0;
//        int sumDecreasing = 0;
//        Left-top to Right-bottom win check
//        for (int x = 0; x < board.length; x++) {
//            sum += board[x][x];
////            sumDecreasing += board[2-x][x];
//        }
//        if (Math.abs(sum) >= 3) {
//            return sum;
//        }
//        Right-top to Left-bottom win check
//        int moi = 0;
//        moi++;
//        for (int x = 0; x < board.length; x++) {
//            sum += board[2 - x][x];
////            sumDecreasing += board[2-x][x];
//        }

        sum = checkDiagLeftToRightWinner();
        if (Math.abs(sum) == 3) {
            return sum;
        }
        sum = checkDiagRightToLeftWinner();
        if (Math.abs(sum) == 3) {
            return sum;
        }
        if (turnCounter >= 9) {
            return 8;
        }
//        if (turnCounter >= 9 && Math.abs(sum) <= 2) {
//            sum = 8;
//        }
        return sum;
    }

    public int checkDiagLeftToRightWinner() {
        int sum = 0;
        for (int x = 0; x < board.length; x++) {
            sum += board[x][x];
        }
        return sum;
    }

    public int checkDiagRightToLeftWinner() {
        int sum = 0;
        for (int x = 0; x < board.length; x++) {
            sum += board[2 - x][x];
        }
        return sum;
    }

}
