package fi.qmppu842.nnttt3.Game.profiles;

import fi.qmppu842.nnttt3.Game.GameStateBean;
import java.awt.Point;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Qmppu842
 */
public abstract class BaseProfile {

//    public abstract int getNextX();
//
//    public abstract int getNextY();
    protected int nextX;
    protected int nextY;
    protected boolean isNextTurnReady;
    @Setter
    protected GameStateBean gameState;
    @Getter
    @Setter
    protected boolean isHumanProfile;

    public BaseProfile() {
        nextX = -1;
        nextY = -1;
        isNextTurnReady = false;
    }

    public BaseProfile(GameStateBean gameState) {
        this.gameState = gameState;
        nextX = -1;
        nextY = -1;
        isNextTurnReady = false;
    }

//    private int getNextX() {
//        if (!isNextTurnReady) {
//            generateNextTurn();
//        }
//        return nextX;
//    }
//
//    private int getNextY() {
//        return nextY;
//    }
    /**
     * Returns next move in array.
     *
     * @return Array that contains nextX and nextY
     */
    public Point getNextTurn() {
        if (!isNextTurnReady) {
            generateNextTurn();
        }
        isNextTurnReady = false;
        return new Point(nextX, nextY);
    }

//    /**
//     * Give this profile current state of game.
//     *
//     * @param gameState
//     */
//    public abstract void giveCurrentGameState(GameStateBean gameState);
    protected abstract void generateNextTurn();
}
