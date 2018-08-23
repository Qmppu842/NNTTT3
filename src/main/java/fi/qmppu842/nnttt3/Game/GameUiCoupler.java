package fi.qmppu842.nnttt3.Game;

import fi.qmppu842.nnttt3.Game.profiles.AI_NeuralNetwork_1.AI_GodClass;
import fi.qmppu842.nnttt3.Game.profiles.BaseProfile;
import fi.qmppu842.nnttt3.Game.profiles.HatStyleProfile;
import fi.qmppu842.nnttt3.Game.profiles.HumanProfile;
import fi.qmppu842.nnttt3.Game.profiles.PerfectStrategyProfile;
import fi.qmppu842.nnttt3.Game.profiles.RandomProfile;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Qmppu842
 */
public class GameUiCoupler {

//    @Setter
    private BaseProfile player1;
//    @Setter
    private BaseProfile player2;
    @Getter
    private ArrayList<BaseProfile> profiles;
    @Getter
    private GameStateBean gameState;

    public GameUiCoupler() {
        gameState = new GameStateBean();
        profiles = new ArrayList<>();
        profiles.add(new RandomProfile(gameState));
//        profiles.add(new HumanProfile(gameState));
//        profiles.add(new PerfectStrategyProfile(gameState));
//        profiles.add(new AI_GodClass(gameState));
//        profiles.add(new HatStyleProfile(gameState));
//
//        profiles.add(new AI_GodClass(gameState, 100));
//        profiles.add(new HatStyleProfile(gameState, 100));
//        profiles.add(new AI_GodClass(gameState, 1000));
//        profiles.add(new HatStyleProfile(gameState, 1000));

//        profiles.get(1).setHumanProfile(true);
    }

    public void addMoreAiLevels() {

        profiles.add(new AI_GodClass(gameState, 200));
        profiles.add(new HatStyleProfile(gameState, 200));
        profiles.add(new AI_GodClass(gameState, 500));
        profiles.add(new HatStyleProfile(gameState, 500));

        profiles.add(new AI_GodClass(gameState, 5000));
        profiles.add(new HatStyleProfile(gameState, 5000));
        profiles.add(new AI_GodClass(gameState, 10000));
        profiles.add(new HatStyleProfile(gameState, 10000));
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void trainTwoAiPlayersAgainstEachOther(String player1, String player2, int forThisManyGames) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param number 1= random profile <br>
     * 2 = Human <br>
     * 3 = Perfect <br>
     * 4 = Ai <br>
     * 5 = Hats <br>
     */
    public void selectPlayer1(int number) {
        player1 = profiles.get(number);
    }

    /**
     *
     * @param number 1= random profile <br>
     * 2 = Human <br>
     * 3 = Perfect <br>
     * 4 = Ai <br>
     * 5 = Hats <br>
     */
    public void selectPlayer2(int number) {
        player2 = profiles.get(number);
    }
//    TODO: hmm... I think this can be easyly done.
//    one needs to not worry about what profile generates.
//    just put what gets here to game board        

    public void humanTurn(int x, int y) {
        int nextTurner = gameState.getNext();
        if (nextTurner == 1 && player1.isHumanProfile()) {

        } else if (nextTurner == -1 && player2.isHumanProfile()) {

        }
        //WTF...
        gameState.makeMove(y, x);

    }

    public void humanTurn(Point nextMove) {
        humanTurn(nextMove.x, nextMove.y);
    }

    public int[][] getBoard() {
        return gameState.getBoard();
    }

    public int[][] setUpNewGame() {
        gameState.setUpNewGame();
        if (player1 != null) {
            player1.setGameState(gameState);
        }
        if (player2 != null) {
            player2.setGameState(gameState);
        }
        return gameState.getBoard();
    }

    /**
     * TODO: delombok things to get proper javadoc <br>
     * Currently -1 means O wins, 0 draw, 1 X wins and 8 game is still going
     *
     * @return
     */
    public int getWinner() {
        return gameState.getWinner();
    }

    /**
     * Returns true if turn was made.
     *
     * @return
     */
    public boolean makeNextNonHumanTurn() {

        int nextTurner = gameState.getNext();
        BaseProfile nexte;
        if (nextTurner == 1 && player1.isHumanProfile()) {
            return false;
        } else if (nextTurner == -1 && player2.isHumanProfile()) {
            return false;
        }
        if (nextTurner == 1) {
            nexte = player1;
        } else {
            nexte = player2;
        }
//        if (nexte.) {
//            
//        }
        Point nextTurn = nexte.getNextTurn();
        gameState.makeMove(nextTurn);
        return true;
    }

    public void noHumans() {
        while (true) {
            System.out.println("Current game state: ");
            System.out.println(Arrays.deepToString(gameState.getBoard()));
            makeNextNonHumanTurn();
            if (gameState.checkAllWinners() == 8
                    || gameState.checkAllWinners() == -3
                    || gameState.checkAllWinners() == 3) {
                break;
            }
        }

    }

}
