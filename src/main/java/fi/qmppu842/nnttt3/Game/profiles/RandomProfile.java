package fi.qmppu842.nnttt3.Game.profiles;

import fi.qmppu842.nnttt3.Game.GameStateBean;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Qmppu842
 */
public class RandomProfile extends BaseProfile {

    private Random rand;
    private ArrayList<Point> possiblities;

    public RandomProfile() {
        rand = new Random();
//        possiblities = new ArrayList<>();
    }

    public RandomProfile(GameStateBean gameState) {
        super(gameState);
         rand = new Random();
    }
    
    

    private void generateNewpossiblities() {
        possiblities = new ArrayList<>();
        int[][] board = gameState.getBoard();
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                if (board[y][x] == 0) {
                    possiblities.add(new Point(x, y));
                }
            }
        }

    }

    @Override
    protected void generateNextTurn() {
        generateNewpossiblities();
        Point thisIsFromPossibilities = possiblities.get(rand.nextInt(possiblities.size()));
        nextX = thisIsFromPossibilities.x;
        nextY = thisIsFromPossibilities.y;
        isNextTurnReady = true;
    }

}
