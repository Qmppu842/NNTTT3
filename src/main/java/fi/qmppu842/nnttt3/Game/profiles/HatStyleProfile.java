package fi.qmppu842.nnttt3.Game.profiles;

import fi.qmppu842.nnttt3.Game.GameStateBean;

/**
 * Make it like the last stick game pseudo-AI. Thou this will have quite complex
 * arrays.
 *
 * @author Qmppu842
 */
public class HatStyleProfile extends BaseProfile {

    public HatStyleProfile(GameStateBean gameState) {
        super(gameState);
    }

    public HatStyleProfile(GameStateBean gameState, int trainedThisManyTimes) {
        super(gameState);
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void generateNextTurn() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
