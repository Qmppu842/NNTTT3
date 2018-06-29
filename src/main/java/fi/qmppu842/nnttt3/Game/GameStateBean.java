package fi.qmppu842.nnttt3.Game;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Arrays;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
        System.out.println("Setup");
        board = new int[3][3];
        System.out.println(Arrays.deepToString(board));
        next = 1;
    }

    public void setUpNewGameCustom() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void makeMove(int x, int y) {
        int oldBoard = board[x][y];
        if (oldBoard == 0) {
            board[x][y] = next;
            next *= -1;
        }
        propChange.firePropertyChange("makeMove", oldBoard, board[x][y]);

    }

    public final int getXSize() {
        return board.length;
    }

    public final int getYSize() {
        return board[0].length;
    }

}
